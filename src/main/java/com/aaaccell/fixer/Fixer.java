package com.aaaccell.fixer;

public class Fixer {

    public static FixerRequestBuilder builder(String accessKey) {
        return new FixerRequestBuilder(FixerServiceGenerator.createService(FixerService.class), accessKey);
    }

}
