package com.gebond.ip.model.setting;

import static org.apache.commons.math3.util.FastMath.pow;

import com.gebond.ip.model.array.ListVector;
import com.gebond.ip.model.array.Vector;
import com.gebond.ip.model.array.Vector2D;
import com.gebond.ip.model.array.Vector3D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 07/05/18.
 */
public class GFieldCacheUtils {

  private static final Map<Vector2D<Integer>, GField> cache = new HashMap<>();
  private static final Map<Vector3D<Integer>, Map<Integer, List<Vector<Integer>>>> cacheByParamsAndNumber = new HashMap<>();

  /**
   * Get field from cache params key is pair of {p , s}.
   */
  public static GField get(Vector2D<Integer> params) {
    GField cached = cache.get(params);
    if (cached == null) {
      synchronized (GFieldCacheUtils.class) {
//      cached = cache.put(params, new GField(params)); //BREAKES DURING TEST, BUT OK DURING DEBUG
        cached = new GField(params);
        cache.put(params, cached);
      }
    }
    return cached;
  }

  /**
   * get field from cache params key is pair of {p, s, N} + number
   */
  public static List<Vector<Integer>> get(Vector3D<Integer> params, int number) {
    Map<Integer, List<Vector<Integer>>> cache = cacheByParamsAndNumber.get(params);
    if (cache == null) {
      synchronized (GFieldCacheUtils.class) {
        cache = buildFilledCache(params);
        cacheByParamsAndNumber.put(params, cache);
      }
    }
    return cache.get(number);
  }

  private static Map<Integer, List<Vector<Integer>>> buildFilledCache(Vector3D<Integer> params) {
    Map<Integer, List<Vector<Integer>>> cache = new HashMap<>();
    int p = params.getX();
    int s = params.getY();
    int n = params.getZ();
    List<List<Vector<Integer>>> allCombinations = new ArrayList<>();
    List<Vector<Integer>> targetList = new ArrayList<>();
    iterateAllCombinations(0, n, new Vector2D<Integer>(p, s), targetList, allCombinations);
    for (List<Vector<Integer>> vectorList : allCombinations) {
      cache.put(convertToNumber(p, s, vectorList), vectorList);
    }
    return cache;
  }

  private static void iterateAllCombinations(int current_counter, int limit_counter,
      Vector2D<Integer> params,
      List<Vector<Integer>> targetList,
      List<List<Vector<Integer>>> allCombinations) {
    if (current_counter == limit_counter) {
      List<Vector<Integer>> list = new ArrayList<>();
      for (Vector<Integer> vector : targetList) {
        list.add(new ListVector<>(vector));
      }
      allCombinations.add(list);
    } else {
      for (Vector<Integer> vector : get(params).alphas) {
        List<Vector<Integer>> copy = new ArrayList<>(targetList);
        copy.add(vector);
        iterateAllCombinations(current_counter + 1, limit_counter, params, copy, allCombinations);
      }
    }
  }

  public static class GField {

    private int p;
    private int s;
    public final List<Vector<Integer>> alphas;

    public GField(Vector2D<Integer> params) {
      this.alphas = new ArrayList<>();
      this.p = params.getX();
      this.s = params.getY();
      setCurrentIndex(-1, 0, new ListVector<>(new Integer[s]));
    }

    private void setCurrentIndex(int current_index, int current_value,
        Vector<Integer> targetVector) {
      if (current_index >= 0) {
        targetVector.set(current_index, current_value);
      }
      if (current_index == this.s - 1) {
        alphas.add(new ListVector<>(targetVector));
      } else {
        for (int i = 0; i < this.p; i++) {
          setCurrentIndex(current_index + 1, i, targetVector);
        }
      }
    }
  }

  public static int convertToNumber(int p, int s, List<Vector<Integer>> alphaVectors) {
    int res = 0;
    for (int n = 0; n < alphaVectors.size(); n++) {
      int summa = 0;
      for (int i = 0; i < s; i++) {
        summa += alphaVectors.get(n).get(i) * pow(p, i);
      }
      res += summa * pow(p, s * n);
    }
    return res;
  }
}
