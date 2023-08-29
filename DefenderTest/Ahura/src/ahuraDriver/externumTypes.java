package ahuraDriver;

public enum externumTypes {
    maximization, minimization;
    public static int toInt(externumTypes type){
        switch (type) {
            case maximization:
                return -1;
            case minimization:
                return 1;
            default:
                return 1;
        }
    }
}