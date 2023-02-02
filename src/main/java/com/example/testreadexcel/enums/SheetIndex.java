package com.example.testreadexcel.enums;

public enum SheetIndex {
    PROVINCE(0),
    DISTRICT(1),
    WARD(2),
    CATEGORY(3),
    INVESTOR(4),
    PROJECT(5),
    REAL_ESTATE(6),
    NEWS(7),
    SOCIAL_POST(8),
    PLANNING_INFORMATION(10),
    DIGITAL_REAL_ESTATE(14);
    Integer value;

    SheetIndex(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static SheetIndex findByValue(int index) {
        for (SheetIndex sheetIndex : SheetIndex.values()) {
            if (sheetIndex.getValue() == index) {
                return sheetIndex;
            }
        }
        return null;
    }
}
