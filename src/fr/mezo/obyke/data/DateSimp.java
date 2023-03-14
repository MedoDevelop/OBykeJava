package fr.mezo.obyke.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateSimp {

	Calendar calendar;
	
	private DateSimp() {
		Date date = new Date();
		this.calendar = Calendar.getInstance();
		this.calendar.setTime(date);
	}
	
	private DateSimp(long timestamp) {
		Date date = new Date(timestamp);
		this.calendar = Calendar.getInstance();
		this.calendar.setTime(date);
	}
	
	public static DateSimp now() {
		return new DateSimp();
	}
	
	public static DateSimp of(long timestamp) {
		return new DateSimp(timestamp);
	}
	
	public static DateSimp of(int jour,int mois,int annee) {
		LocalDate ldate = LocalDate.of(annee,mois,jour);

        // Convertir l'objet LocalDate en LocalDateTime avec une heure à minuit
        LocalDateTime ldateTime = ldate.atStartOfDay();

        // Obtenir le timestamp Unix en secondes à partir de l'objet LocalDateTime
        long timestamp = ldateTime.toEpochSecond(java.time.ZoneOffset.UTC) * 1000;
        return new DateSimp(timestamp);
	}
	
	public int getJour() {
		return this.calendar.get(Calendar.DAY_OF_MONTH);
	}

	public int getMois() {
		return this.calendar.get(Calendar.MONTH) + 1;
	}
	
	public int getAnnee() {
		return this.calendar.get(Calendar.YEAR);
	}
	
	public long getTimestamp() {
        return this.calendar.getTimeInMillis();
    }
	
	public String toString() {
		return this.getJour() + "/" + this.getMois() + "/" + this.getAnnee();
	}
	
	
}
