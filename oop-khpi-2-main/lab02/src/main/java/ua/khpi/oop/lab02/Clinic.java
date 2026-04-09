package ua.khpi.oop.lab02;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clinic {
    private final String name;
    private final List<Patient> patients = new ArrayList<>();
    private final List<Doctor> doctors = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();

    public Clinic(String name) {
        this.name = Objects.requireNonNull(name, "Назва клініки не може бути null");
    }

    public void addPatient(Patient patient) {
        if (patient != null && !patients.contains(patient)) patients.add(patient);
    }

    public void addDoctor(Doctor doctor) {
        if (doctor != null && !doctors.contains(doctor)) doctors.add(doctor);
    }

    public Appointment scheduleAppointment(Patient patient, Doctor doctor, String date) {
        if (patient == null || doctor == null || !doctor.canAcceptPatient()) return null;
        Appointment appt = new Appointment("A-" + (appointments.size() + 1), patient, doctor, date);
        appointments.add(appt);
        patient.scheduleAppointment(doctor);
        doctor.markBusy();
        return appt;
    }

    public Doctor findAvailableDoctorBySpecialization(String specialization) {
        return doctors.stream()
                .filter(d -> d.getSpecialization().equalsIgnoreCase(specialization) && d.canAcceptPatient())
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return String.format("Clinic{name='%s', patients=%d, doctors=%d, appointments=%d}",
                name, patients.size(), doctors.size(), appointments.size());
    }
}