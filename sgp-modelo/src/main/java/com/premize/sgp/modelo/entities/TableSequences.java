package com.premize.sgp.modelo.entities;

/**
 * Tabla para la generación de secuencias JPA, strategy=GenerationType.TABLE se define como un uso más portable de los
 * consecutivos automaticos
 * 
 * @author <a href="mailto:juanp.alvarez@premize.com"> Juan Pablo Alvarez </a>
 * @project pmz-profile-modelo
 * @class GeneratorTable
 * @date 16/09/2013
 */
public interface TableSequences {
	
	final String NAME = "sgp_sequences";
	final String PK_COLUMN_NAME = "entity_name";
	final String VALUE_COLUMN_NAME = "entity_sq";
	final int ALLOCATION_SIZE = 1;
	final int INITIAL_VALUE = 1;
}
