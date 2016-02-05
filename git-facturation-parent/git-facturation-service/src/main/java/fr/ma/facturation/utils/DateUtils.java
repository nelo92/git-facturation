package fr.ma.facturation.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;

/**
 * Classe utilitaire de manipulation des dates
 */
public class DateUtils {

	/**
	 * Format à utiliser par défaut pour l'affichage des dates dans l'application
	 */
	public static final String	SHORT_DATE_FORMAT	= "dd/MM/yyyy";
	/**
	 * Format à utiliser par défaut pour l'affichage des heures dans l'application
	 */
	public static final String	DEFAULT_HOUR_FORMAT	= "HH:mm";
	/**
	 * Format à utiliser par défaut pour l'affichage des heures dans l'application
	 */
	public static final String	LONG_DATE_FORMAT	= "dd/MM/yyyy HH:mm:ss:SSS";
	/**
	 * Format à utiliser par défaut pour l'affichage des heures dans la base de donnée.
	 */
	public static final String	LONG_SQL_DATE_FORMAT	= "dd/MM/yyyy HH:mm:ss";

	/**
	 * Format à utiliser par défaut pour l'affichage des dates dans le nom du fichier de tracker
	 */
	public static final String	TRACK_DATE_FORMAT	= "yyyyMMdd";
	/**
	 * Constante indiquant un ajout
	 */
	public static final int		AJOUT				= 1;
	/**
	 * Constante indiquant un retrait
	 */
	public static final int		RETRAIT				= 2;
	/**
	 * Constante représentant une journée <br>
	 * Surcharge Calendar.DAY_OF_YEAR
	 */
	public static final int		JOUR				= Calendar.DAY_OF_YEAR;
	/**
	 * Constante représentant une semaine <br>
	 * Surcharge Calendar.WEEK_OF_YEAR
	 */
	public static final int		SEMAINE				= Calendar.WEEK_OF_YEAR;
	/**
	 * Constante représentant un mois <br>
	 * Surcharge Calendar.MONTH
	 */
	public static final int		MOIS				= Calendar.MONTH;
	/**
	 * Constante représentant une année <br>
	 * Surcharge Calendar.YEAR
	 */
	public static final int		ANNEE				= Calendar.YEAR;

	/**
	 * Convertit un objet java.util.Date en un objet Calendar
	 *
	 * @param date
	 *            objet java.util.Date
	 * @return objet Calendar correspondant à la date fournie en entrée
	 */
	public static Calendar dateToCalendar(Date date) {
		// assert date != null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * Crée un objet Calendar à partir d'une chaîne de caractères passée en paramètre
	 *
	 * @param sDate
	 *            la date sous forme de chaîne de caractères
	 * @return l'obejt Calendar
	 * @throws ParseException
	 *             Si la chaîne ne peut pas être formatée
	 */
	public static Calendar stringToCalendar(String sDate) throws ParseException {
		return dateToCalendar(stringToDate(sDate));
	}

	/**
	 * Convertit un objet Calendar en un objet java.util.Date
	 *
	 * @param calendar
	 *            objet Calendar
	 * @return objet java.util.Date correspondant à la date fournie en entrée
	 */
	public static Date calendarToDate(Calendar calendar) {
		return calendar.getTime();
	}

	/**
	 * Convertit un objet Calendar en un objet java.sql.Timestamp
	 *
	 * @param calendar
	 *            objet Calendar
	 * @return objet java.sql.Timestamp correspondant à la date fournie en entrée
	 */
	public static Timestamp toTimestamp(Calendar calendar) {
		return new Timestamp(calendarToDate(calendar).getTime());
	}

	/**
	 * Convertit un objet Date en un objet java.sql.Timestamp
	 *
	 * @param calendar
	 *            objet Calendar
	 * @return objet java.sql.Timestamp correspondant à la date fournie en entrée
	 */
	public static Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * Crée un objet Date à partir d'une chaîne de caractères passée en paramètre
	 *
	 * @param sDate
	 *            la date sous forme de chaîne de caractères
	 * @return l'objet Date
	 * @throws ParseException
	 *             Si la chaîne ne peut pas être formatée
	 */
	public static Date stringToDate(String sDate) throws ParseException {
		return formatDate(sDate, SHORT_DATE_FORMAT);
	}

	public static String dateToString(Date date) {
		return DateFormatUtils.format(date, DateUtils.SHORT_DATE_FORMAT);
	}

	public static String dateToLongString(Date date) {
		return DateFormatUtils.format(date, DateUtils.LONG_DATE_FORMAT);
	}

	public static String dateToLongSQLString(Date date) {
		return DateFormatUtils.format(date, DateUtils.LONG_SQL_DATE_FORMAT);
	}

	/**
	 * Crée un objet Calendar à partir d'une chaîne de caractères et un format passés en paramètre
	 *
	 * @param sDate
	 *            la date sous forme de chaîne de caractères
	 * @param pattern
	 *            le format souhaité pour la Calendar
	 * @return l'objet Calendar
	 * @throws ParseException
	 *             Si la chaîne ne peut pas être formatée
	 */
	public static Calendar dateToCalendar(String sDate, String pattern) throws ParseException {
		return dateToCalendar(formatDate(sDate, pattern));
	}

	/**
	 * retourne la date actuelle
	 *
	 * @return objet java.util.Date correspondant à la date actuelle
	 */
	public static Date now() {
		return new Date();
	}

	/**
	 * retourne l'heure actuelle
	 *
	 * @return objet java.util.Timestamp correspondant à l'heure courante
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * retourne la date actuelle avec l'heure à minuit 00:00:0
	 *
	 * @return objet java.util.Timestamp correspondant à l'heure courante
	 */
	public static Date today() {
		return org.apache.commons.lang.time.DateUtils.truncate(DateUtils.now(), Calendar.DAY_OF_MONTH);
	}

	/**
	 * retourne la date actuelle en format dd/MM/yyyy
	 *
	 * @return objet String correspondant à la date actuelle
	 */
	public static String toShortString() {
		return DateFormatUtils.format(DateUtils.today(), DateUtils.SHORT_DATE_FORMAT);
	}

	/**
	 * retourne la date actuelle en format dd/MM/yyy HH:mm:ss:SSS
	 *
	 * @return objet String correspondant à la date actuelle
	 */
	public static String toLongString() {
		return DateFormatUtils.format(new Date(), DateUtils.LONG_DATE_FORMAT);
	}

	/**
	 * retourne la date actuelle en format dd/MM/yyy HH:mm:ss
	 *
	 * @return objet String correspondant à la date actuelle
	 */
	public static String toLongSQLString() {
		return DateFormatUtils.format(new Date(), DateUtils.LONG_SQL_DATE_FORMAT);
	}

	/**
	 * retourne la date actuelle en format dd/MM/yyy HH:mm:ss:SSS
	 *
	 * @return objet String correspondant à la date actuelle
	 */
	public static String toTrackString() {
		return DateFormatUtils.format(new Date(), DateUtils.TRACK_DATE_FORMAT);
	}

	public static String toTrackString(Calendar calendar) {
		return DateFormatUtils.format(calendarToDate(calendar), DateUtils.TRACK_DATE_FORMAT);
	}

	/**
	 * retourne l'année d'une date
	 *
	 * @param Date
	 *            la date
	 * @return objet int correspondant l'année
	 */
	public static int getYear(Date date) {
		Calendar cal = dateToCalendar(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * retourne l'année d'une date
	 *
	 * @param Date
	 *            la date
	 * @return objet int correspondant à l'année
	 */
	public static int getYear() {
		Calendar cal = dateToCalendar(now());
		return cal.get(Calendar.YEAR);
	}

	/**
	 * retourne le mois d'une date
	 *
	 * @param Date
	 *            la date
	 * @return objet int correspondant au mois
	 */
	public static int getMonth(Date date) {
		Calendar cal = dateToCalendar(date);
		return cal.get(Calendar.MONTH);
	}

	/**
	 * retourne le mois d'une date
	 *
	 * @return objet int correspondant au mois
	 */
	public static int getMonth() {
		Calendar cal = dateToCalendar(now());
		return cal.get(Calendar.MONTH);
	}

	/**
	 * retourne le jour d'une date
	 *
	 * @param Date
	 *            la date
	 * @return objet int correspondant au jour
	 */
	public static int getDay(Date date) {
		Calendar cal = dateToCalendar(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * retourne la jour courant
	 *
	 * @return objet int correspondant au jour
	 */
	public static int getDay() {
		Calendar cal = dateToCalendar(now());
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Crée un objet Date à partir d'une chaîne de caractères et un format passés en paramètre
	 *
	 * @param sDate
	 *            la date sous forme de chaîne de caractères
	 * @param pattern
	 *            le format souhaité pour la date
	 * @return l'objet date
	 * @throws ParseException
	 *             Si la chaîne ne peut pas être formatée sauf si elle est nulle
	 */
	public static Date formatDate(String sDate, String pattern) throws ParseException {
		// return new SimpleDateFormat(pattern).parse(sDate);
		if (StringUtils.isNotBlank(sDate)) {
			return org.apache.commons.lang.time.DateUtils.parseDate(sDate.replaceAll("\\s", ""), new String[] { pattern });
		}
		return null;
	}
	/**
	 * Crée un objet String à partir d'un objet TimeStamp
	 *
	 * @param sTamp
	 *            Objet TimeStamp
	 * @param pattern
	 *            le format souhaité pour la date
	 * @return String formaté
	 * @throws ParseException
	 *             Si la chaîne ne peut pas être formatée sauf si elle est nulle
	 */
	public static String formatTimestampToString(java.sql.Timestamp stamp){

        Date date = new Date(stamp.getTime());
        SimpleDateFormat format = new SimpleDateFormat(SHORT_DATE_FORMAT);
        return format.format(date);

}

	/**
	 * Formatte un objet Date avec un format donné
	 *
	 * @param date
	 *            date à formatter
	 * @param formatPattern
	 *            format désiré
	 * @return chaîne de caractères résultat du formatage de la date
	 */
	public static String format(Date date, String formatPattern) {
		return DateFormatUtils.format(date, formatPattern);
	}

	/**
	 * Formatte un objet Calendar avec un format donné
	 *
	 * @param calendar
	 *            date à formatter
	 * @param formatPattern
	 *            format désiré
	 * @return chaîne de caractères résultat du formatage de la date
	 */
	public static String format(Calendar calendar, String formatPattern) {
		return format(calendarToDate(calendar), formatPattern);
	}

	/**
	 * Ajoute un nombre de jour à une date
	 *
	 * @param date
	 *            à incrémenter
	 * @param nbDays
	 *            nombre de jours
	 * @return Date incrémentée de la date
	 */
	public static Date addDaysToDate(Date date, int nbDays) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(org.apache.commons.lang.time.DateUtils.truncate(date, Calendar.DAY_OF_MONTH));
		cal.add(Calendar.DATE, nbDays);
		return cal.getTime();
	}

	/**
	 * Ajoute un nombre de jour à la date courante
	 *
	 * @param nbDays
	 *            nombre de jours
	 * @return Date incrémentée de la date
	 */
	public static Date addDaysToDay(int nbDays) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(today());
		return calendarToDate(DateUtils.calculateNewDate(cal, DateUtils.AJOUT, nbDays, DateUtils.JOUR));
	}

	/**
	 * Ajoute ou soustrait une quantité d'unités données à une date donnée
	 *
	 * @param date
	 *            date initiale
	 * @param operation
	 *            code de l'opération (DateUtils.AJOUT ou DateUtils.RETRAIT)
	 * @param quantite
	 *            quantité à ajouter ou retirer de la date initiale
	 * @param unite
	 *            unité de la quantité à ajouter ou retirer (DateUtils.JOUR, DateUtils.SEMAINE, DateUtils.MOIS, DateUtils.ANNEE)
	 * @return date résultat de l'opération
	 */
	public static Calendar calculateNewDate(Calendar date, int operation, int quantite, int unite) {
		switch (operation) {
		case AJOUT:
			date.add(unite, quantite);
			break;
		case RETRAIT:
			date.add(unite, -quantite);
			break;
		}
		return date;
	}

	public static String getLocalMonth(int idMonth, Locale locale) {
		Calendar cal = Calendar.getInstance(locale);
		cal.set(Calendar.YEAR, 1970);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, idMonth);
		FastDateFormat fdf = FastDateFormat.getInstance("MMMMM", locale);
		return StringUtils.capitalize(fdf.format(cal.getTime()));
	}

	public static int getLastWeekOfYear(){
		Calendar calendarNow = Calendar.getInstance();
		return calendarNow.getActualMaximum(Calendar.WEEK_OF_YEAR);
	}

	public static int getLastWeekOfYear(int annee){
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.set(Calendar.YEAR, annee);
		return calendarNow.getActualMaximum(Calendar.WEEK_OF_YEAR);
	}

	public static int getActualWeekOfYear(){
		return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
	}

}
