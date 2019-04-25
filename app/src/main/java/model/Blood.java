package model;

public enum Blood{
    ZERO_POSITIVE(0){
        @Override
        public String toString(){
            return "0+";
        }
    },
    ZERO_NEGATIVE(1){
        @Override
        public String toString(){
            return "0-";
        }
    },
    A_POSITIVE(2){
        @Override
        public String toString(){
            return "A+";
        }
    },
    A_NEGATIVE(3){
        @Override
        public String toString(){
            return "A-";
        }
    },
    B_POSITIVE(4){
        @Override
        public String toString(){
            return "B+";
        }
    },
    B_NEGATIVE(5){
        @Override
        public String toString(){
            return "B-";
        }
    },
    AB_POSITIVE(6){
        @Override
        public String toString(){
            return "AB+";
        }
    },
    AB_NEGATIVE(7){
        @Override
        public String toString(){
            return "AB-";
        }
    };

    private int intValue;
    private Blood(int intValue){
        this.intValue = intValue;
    }

    public static Blood bloodFactory(int intValue){
        for( Blood b : Blood.values() )
            if(b.getIntValue() == intValue)
                return b;

        return null;
    }

    public static Blood bloodFactory(String blood){
        if( blood == null)
            return null;

        for( Blood b : Blood.values() )
            if( b.toString().equals(blood))
                return b;
        return null;
    }

    public boolean isValid(String blood){
        return bloodFactory(blood) != null;
    }

    public boolean isValid(int intValue){
        return bloodFactory(intValue) != null;
    }

    public int getIntValue() {
        return intValue;
    }

    public abstract String toString();
}