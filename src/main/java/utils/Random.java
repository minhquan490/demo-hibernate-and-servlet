package utils;

public class Random {

    public static String getID(String type){
        String typeID = "";

        switch (type) {
            case "product":
                typeID = "P-" + ID();
                break;
            case "user":
                typeID = UID();
                break;
            case "card":
                typeID = "C-" + ID();
                break;
            case "order":
                typeID = "HD-" + ID();
                break;
            case "bill":
                typeID = "B-" + ID();
                break;
        }
        return typeID;
    }

    private static String UID() {
        String AlphaString = "0123456789";
        StringBuilder sb = new  StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int index = (int) (AlphaString.length() * Math.random());
            sb.append(AlphaString.charAt(index));
        }
        return sb.toString();
    }

    private static String ID() {
        String AlphaString = "0123456789" + "QWERTYUIOPASDFGHJKLZXCVBNM" + "qwertyuiopasdfghjklzxcvbnm";
        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int index = (int) (AlphaString.length() * Math.random());
            sb.append(AlphaString.charAt(index));
        }
        return sb.toString();
    }
}