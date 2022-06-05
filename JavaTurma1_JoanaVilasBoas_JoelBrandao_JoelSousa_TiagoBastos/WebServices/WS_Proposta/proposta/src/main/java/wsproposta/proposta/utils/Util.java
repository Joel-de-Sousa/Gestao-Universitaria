package wsproposta.proposta.utils;

public class Util {
    public static boolean validaStringComMinSemAlgSemSimbolo(int min,int max, String sValidar){
        boolean valida = false;
        if (sValidar != null  && (sValidar.length() > min && sValidar.length()<max)) { //Verifica null, tamanho min e max
            char c;
            for (int i = 0; i < sValidar.length(); i++) {
                c = sValidar.charAt(i);
                if (Character.isAlphabetic(c)) { //verifica se é letra
                    valida = true;
                } else {
                    valida = false;
                    break;
                }
            }
        } else {
            valida = false;
        }
        return valida;
    }


    public static boolean validaStringMinCarateresNaoBrancos(int min, String sValidar){

        boolean valido = false;
        if (sValidar != null  && (sValidar.replaceAll(" ","").length()>=min)){
            valido=true;
        }
        return valido;
    }
}
