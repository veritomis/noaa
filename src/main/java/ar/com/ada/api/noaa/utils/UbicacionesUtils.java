package ar.com.ada.api.noaa.utils;

public class UbicacionesUtils {

    public static boolean validarLatitud(Double latitud) {
        if (Math.abs(latitud) >= 0 && Math.abs(latitud) <= 90) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validarLongitud(Double longitud) {
        if (Math.abs(longitud) <= 180) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validarCoordenadas(double longitud, double latitud) {
        if (validarLatitud(latitud) && validarLongitud(longitud)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validarCoordenadasFueraDelPlaneta(double longitud, double latitud) {

        return !validarCoordenadas(longitud, latitud);
    }

    public static boolean validarSiEsHemisferioNorte(double latitud, double longitud) {
        if (!validarCoordenadas(longitud, latitud))
            return false;
        if (latitud >= 0 && latitud <= 90) {
            return true;
        } else {
            return false;
        }

    }

}
