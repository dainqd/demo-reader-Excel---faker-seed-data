package com.example.testreadexcel.enums;

public enum SheetIndex {
    PROVINCE("Province"),
    DISTRICT("District"),
    WARD("Ward"),
    CATEGORY("Category"),
    STUDENT("Student");
    String value;

    SheetIndex(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SheetIndex findByValue(int name) {
        for (SheetIndex sheetIndex : SheetIndex.values()) {
            if (sheetIndex.getValue().equals(name)) {
                return sheetIndex;
            }
        }
        return null;
    }
}
