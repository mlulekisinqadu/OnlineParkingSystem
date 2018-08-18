package com.bryansinqadu.eparking_app;

public abstract class AbstractChat {

        public abstract String getName();

        public abstract String getMessage();

        public abstract String getUid();

        @Override
        public abstract int hashCode();

        @Override
        public abstract boolean equals(Object obj);
}
