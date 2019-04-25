package model;

public enum Gender{
    MALE(0){
        @Override
        public String toString(){
            return "Male";
        }
    }, FEMALE(1){
        @Override
        public String toString(){
            return "Female";
        }
    };

    private final int intValue;
    private Gender(int intValue){
        this.intValue = intValue;
    }

    public static Gender genderFactory(int intValue){
        for( Gender g : Gender.values() )
            if(g.getIntValue() == intValue)
                return g;
        return null;
    }

    public boolean isValid(int intValue){
        return genderFactory(intValue) != null;
    }

    public static Gender genderFactory(String gender){
        if( gender == null)
            return null;
        for( Gender g : Gender.values() )
            if(g.toString().equals(gender))
                return g;
        return null;
    }

    public boolean isValid(String gender){
        return genderFactory(gender) != null;
    }

    public int getIntValue() {
        return intValue;
    }
    public abstract String toString();
}