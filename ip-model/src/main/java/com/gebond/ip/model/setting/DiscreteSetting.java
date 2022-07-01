package com.gebond.ip.model.setting;

/**
 * Created on 07/05/18.
 */
public class DiscreteSetting {

  private int p;
  private int s;
  private int N;
  private int size;

  public DiscreteSetting() {
  }

  public int getP() {
    return p;
  }

  public void setP(int p) {
    this.p = p;
  }

  public int getS() {
    return s;
  }

  public void setS(int s) {
    this.s = s;
  }

  public int getN() {
    return N;
  }

  public void setN(int n) {
    N = n;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public static DiscreteSettingBuilder newDiscreteBuilder() {
    return new DiscreteSettingBuilder();
  }

  public static final class DiscreteSettingBuilder {

    private int p;
    private int s;
    private int N;
    private int size;

    private DiscreteSettingBuilder() {
    }

    public static DiscreteSettingBuilder aDiscreteSetting() {
      return new DiscreteSettingBuilder();
    }

    public DiscreteSettingBuilder withP(int p) {
      this.p = p;
      return this;
    }

    public DiscreteSettingBuilder withS(int s) {
      this.s = s;
      return this;
    }

    public DiscreteSettingBuilder withN(int N) {
      this.N = N;
      return this;
    }

    public DiscreteSettingBuilder withSize(int size) {
      this.size = size;
      return this;
    }

    public DiscreteSetting build() {
      DiscreteSetting discreteSetting = new DiscreteSetting();
      discreteSetting.setP(p);
      discreteSetting.setS(s);
      discreteSetting.setN(N);
      discreteSetting.setSize(size);
      return discreteSetting;
    }
  }
}
