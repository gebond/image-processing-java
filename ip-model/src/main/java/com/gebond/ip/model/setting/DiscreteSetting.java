package com.gebond.ip.model.setting;

/**
 * Created on 07/05/18.
 */
public class DiscreteSetting {

  private int p;
  private int s;

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

  public static DiscreteSettingBuilder newDiscreteBuilder() {
    return new DiscreteSettingBuilder();
  }

  public static final class DiscreteSettingBuilder {

    private int p;
    private int s;

    private DiscreteSettingBuilder() {
    }

    public DiscreteSettingBuilder withP(int p) {
      this.p = p;
      return this;
    }

    public DiscreteSettingBuilder withS(int s) {
      this.s = s;
      return this;
    }

    public DiscreteSetting build() {
      DiscreteSetting discreteSetting = new DiscreteSetting();
      discreteSetting.setP(p);
      discreteSetting.setS(s);
      return discreteSetting;
    }
  }
}
