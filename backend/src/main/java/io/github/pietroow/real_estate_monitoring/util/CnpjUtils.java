package io.github.pietroow.real_estate_monitoring.util;

public final class CnpjUtils {
    private CnpjUtils() {
    }

    public static String onlyDigits(String cnpj) {
        return cnpj == null ? null : cnpj.replaceAll("\\D", "");
    }

    public static String toMasked(String digits14) {
        if (digits14 == null) return null;
        var d = onlyDigits(digits14);
        if (d.length() != 14) return digits14;
        return d.substring(0, 2) + "." + d.substring(2, 5) + "." + d.substring(5, 8) + "/"
                + d.substring(8, 12) + "-" + d.substring(12, 14);
    }
}
