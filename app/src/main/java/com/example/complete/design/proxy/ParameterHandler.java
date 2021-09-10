package com.example.complete.design.proxy;

public abstract class ParameterHandler {

    abstract void apply(ServiceMethod serviceMethod , String value);

    static class FieldParameterHandler extends ParameterHandler {

        private String key;

        public FieldParameterHandler(String key) {
            this.key = key;
        }

        @Override
        void apply(ServiceMethod serviceMethod, String value) {
            serviceMethod.addFieldParameter(key , value);
        }
    }

    static class QueryParameterHandler extends ParameterHandler{

        private String key;

        public QueryParameterHandler(String key) {
            this.key = key;
        }

        @Override
        void apply(ServiceMethod serviceMethod, String value) {
            serviceMethod.addQueryParameter(key , value);
        }
    }


}
