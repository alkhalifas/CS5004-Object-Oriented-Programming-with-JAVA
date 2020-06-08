package cs5004.questionnaire;

enum LikertResponseOption {
  STRONGLY_DISAGREE(-2, "Strongly Disagree"),
  DISAGREE(-1, "Disagree"),
  NEITHER_AGREE_NOR_DISAGREE(0,"Neither agree nor Disagree"),
  AGREE(1,"Agree"),
  STRONGLY_AGREE(2,"Strongly Agree");


  private final int resVal;
  private final String resStr;

  LikertResponseOption(int resVal, String resStr) {
    this.resVal = resVal;
    this.resStr = resStr;
  }

  public int getValue() {
    return resVal;
  }

  public String getString() {
    return resStr;
  }
}

