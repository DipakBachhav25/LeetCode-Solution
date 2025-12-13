class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        Map<String, Integer> BUSINESS_ORDER = Map.of(
        "electronics", 0,
        "grocery", 1,
        "pharmacy", 2,
        "restaurant", 3
    );

        List<List<String>> buckets = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int i = 0; i < code.length; i++) {
            if (isActive[i] &&
                BUSINESS_ORDER.containsKey(businessLine[i]) &&
                isValidCouponCode(code[i])) {
                int bucketIndex = BUSINESS_ORDER.get(businessLine[i]);
                buckets.get(bucketIndex).add(code[i]);
            }
        }

        List<String> result = new ArrayList<>();
        for (List<String> bucket : buckets) {
            Collections.sort(bucket);
            result.addAll(bucket);
        }

        return result;
    }

    private static boolean isValidCouponCode(String couponCode) {
        return !couponCode.isEmpty() && couponCode.matches("[A-Za-z0-9_]+");
    }
}