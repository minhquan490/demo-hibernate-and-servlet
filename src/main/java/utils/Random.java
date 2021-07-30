package utils;

public class Random {

    public static String getID(String type){
        String typeID = "";

        switch (type) {
            case "product":
                typeID = "P-";
                break;
            case "user":
                typeID = "U-";
                break;
            case "card":
                typeID = "C-";
                break;
            case "order":
                typeID = "HD-";
                break;
        }

        String AlphaString = "0123456789" + "QWERTYUIOPASDFGHJKLZXCVBNM" + "qwertyuiopasdfghjklzxcvbnm";
        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int index = (int) (AlphaString.length() * Math.random());
            sb.append(AlphaString.charAt(index));
        }
        return typeID + sb.toString();
    }
}