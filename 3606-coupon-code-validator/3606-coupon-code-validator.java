class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        Map<String, Integer> BUSINESS_ORDER = Map.of(
        "electronics", 0,
        "grocery", 1,
        "pharmacy", 2,
        "restaurant", 3
    );

    List<Coupon> validCoupons = new ArrayList<>();

        for (int i = 0; i < code.length; i++) {
            if (isActive[i] &&
                BUSINESS_ORDER.containsKey(businessLine[i]) &&
                isValidCouponCode(code[i])) {
                validCoupons.add(new Coupon(code[i], businessLine[i]));
            }
        }

        validCoupons.sort((c1, c2) -> {
            int cmp = Integer.compare(BUSINESS_ORDER.get(c1.businessLine),
                                      BUSINESS_ORDER.get(c2.businessLine));
            return (cmp != 0) ? cmp : c1.code.compareTo(c2.code);
        });

        return validCoupons.stream().map(c -> c.code).toList();
    }

    private static boolean isValidCouponCode(String couponCode) {
        return !couponCode.isEmpty() && couponCode.matches("[A-Za-z0-9_]+");
    }

    private static class Coupon {
        String code;
        String businessLine;
        Coupon(String code, String businessLine) {
            this.code = code;
            this.businessLine = businessLine;
        }
    }
}