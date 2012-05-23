package br.com.fragment.config;

public class Settings {
	
	private static boolean autenticado;

	public static boolean isAutenticado() {
		return autenticado;
	}

	public static void setAutenticado(boolean autenticado) {
		Settings.autenticado = autenticado;
	}

}
