package com.lucasbrandao.cadastroclientes.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
	
	public static void log(String mensagem, String tipo, Class<?> classe) {
		Logger logger = LoggerFactory.getLogger(classe.getCanonicalName());
		
		switch (tipo) {
		case "info":
			logger.info(mensagem);
			
			break;
		case "erro":
			logger.error(mensagem);
			
			break;
		case "alerta":
			logger.warn(mensagem);
			
			break;
			
		default:
			logger.warn("Tipo de log inválido.");
		}
	}
}
