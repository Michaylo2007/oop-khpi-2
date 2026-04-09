package ua.khpi.oop.lab02;

import java.util.Objects;

/**
 * Represents a scheduled appointment between a patient and a doctor.
 * Links a Patient with a Doctor on a specific date.
 *
 * @author Student
 */
public class Appointment {

    // --- ПОЛЯ ---
    private final String appointmentId; // Унікальний ідентифікатор запису (final — не змінюється)
    private Patient patient;            // Пацієнт, якому належить запис
    private Doctor doctor;              // Лікар, до якого запис
    private String date;                // Дата прийому у форматі "YYYY-MM-DD" (рядок для простоти)
    private String status;              // Статус: "SCHEDULED", "COMPLETED", "CANCELLED"

    // --- КОНСТРУКТОРИ ---

    /**
     * Повний конструктор — вказуємо всі дані запису явно.
     */
    public Appointment(String appointmentId, Patient patient, Doctor doctor,
                       String date, String status) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.status = status;
    }

    /**
     * Скорочений конструктор — новий запис одразу зі статусом "SCHEDULED".
     */
    public Appointment(String appointmentId, Patient patient, Doctor doctor, String date) {
        this(appointmentId, patient, doctor, date, "SCHEDULED"); // Статус за замовчуванням
    }

    // --- МЕТОДИ ПРЕДМЕТНОЇ ГАЛУЗІ ---

    /**
     * Позначає запис як завершений (прийом відбувся).
     */
    public void complete() {
        this.status = "COMPLETED"; // Змінюємо статус
    }

    /**
     * Скасовує запис.
     */
    public void cancel() {
        this.status = "CANCELLED";
    }

    /**
     * Перевіряє, чи запис ще активний (не скасований і не завершений).
     *
     * @return true якщо статус "SCHEDULED"
     */
    public boolean isActive() {
        return "SCHEDULED".equals(status); // Безпечне порівняння рядків через equals (не ==)
    }

    // --- ГЕТЕРИ ---

    public String getAppointmentId() { return appointmentId; }
    public Patient getPatient()      { return patient; }
    public Doctor getDoctor()        { return doctor; }
    public String getDate()          { return date; }
    public String getStatus()        { return status; }

    // --- СПЕЦІАЛЬНІ МЕТОДИ ---

    @Override
    public String toString() {
        // Форматований рядок зі всіма ключовими даними запису
        return String.format("Appointment{id='%s', patient='%s', doctor='%s', date='%s', status='%s'}",
                appointmentId,
                patient != null ? patient.getFullName() : "none",  // Захист від null
                doctor  != null ? doctor.getFullName()  : "none",
                date,
                status);
    }

    /**
     * Рівність визначається за appointmentId — кожен запис унікальний.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Appointment other)) return false;
        return Objects.equals(appointmentId, other.appointmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId);
    }
}
