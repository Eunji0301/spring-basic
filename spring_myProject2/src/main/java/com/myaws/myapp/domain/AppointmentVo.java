package com.myaws.myapp.domain;

public class AppointmentVo {
	private int aidx;
	private int pidx;
	private int didx;
	private String appointmentDate;
	private String appointmentTime;
	private String appointmentSubject;;
	private String doctorInCharge;
	private String patientName;
	private String patientPhone;
	private String patientId;
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientPhone() {
		return patientPhone;
	}
	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	public int getAidx() {
		return aidx;
	}
	public void setAidx(int aidx) {
		this.aidx = aidx;
	}
	public int getPidx() {
		return pidx;
	}
	public void setPidx(int pidx) {
		this.pidx = pidx;
	}
	public int getDidx() {
		return didx;
	}
	public void setDidx(int didx) {
		this.didx = didx;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public String getAppointmentSubject() {
		return appointmentSubject;
	}
	public void setAppointmentSubject(String appointmentSubject) {
		this.appointmentSubject = appointmentSubject;
	}

	public String getDoctorInCharge() {
		return doctorInCharge;
	}
	public void setDoctorInCharge(String doctorInCharge) {
		this.doctorInCharge = doctorInCharge;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
}
