package it.dvel.tirocinio.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation da associare ai campi delle classi del model che rappresentano
 * l'oggetto legato ad una foreign key. In realtà non viene MAI utilizzata,
 * serve solo a fini didattici per illustrare le annotation custom.
 * 
 * @author Marco Napolitano
 */
@Retention(RetentionPolicy.SOURCE)
public @interface ForeignKey {
	public String field();

	public Loading loading() default Loading.LAZY;
}