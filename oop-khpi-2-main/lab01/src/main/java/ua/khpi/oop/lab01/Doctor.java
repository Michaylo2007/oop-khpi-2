package ua.khpi.oop.lab01; // Той самий пакет — всі класи лабораторної роботи тут

import java.util.Objects;

/**
 * Represents a doctor working in the private clinic.
 *
 * @author Student
 */
public class Doctor {

    // --- ПОЛЯ ---
    private final String doctorId;  // Унікальний ідентифікатор лікаря (final — не змінюється)
    private String fullName;        // Повне ім'я лікаря
    private String specialization;  // Спеціалізація, наприклад "Therapist", "Surgeon"
    private boolean available;      // Чи доступний лікар для прийому

    // --- КОНСТРУКТОРИ ---

    /**
     * Повний конструктор.
     *
     * @param doctorId       ідентифікатор
     * @param fullName       ім'я лікаря
     * @param specialization спеціалізація
     * @param available      чи доступний зараз
     */
    public Doctor(String doctorId, String fullName, String specialization, boolean available) {
        this.doctorId = doctorId;
        this.fullName = fullName;
        this.specialization = specialization;
        this.available = available;
    }

    /**
     * Скорочений конструктор — лікар одразу вважається доступним (available = true).
     *
     * @param doctorId       ідентифікатор
     * @param fullName       ім'я
     * @param specialization спеціалізація
     */
    public Doctor(String doctorId, String fullName, String specialization) {
        this(doctorId, fullName, specialization, true); // Делегуємо до повного конструктора
    }

    // --- МЕТОДИ ПРЕДМЕТНОЇ ГАЛУЗІ ---

    /**
     * Перевіряє, чи може лікар прийняти нового пацієнта.
     *
     * @return true якщо лікар доступний
     */
    public boolean canAcceptPatient() {
        return available; // Просто повертаємо значення поля
    }

    /**
     * Відмічає лікаря як зайнятого (наприклад, під час прийому).
     */
    public void markBusy() {
        this.available = false;
    }

    /**
     * Відмічає лікаря як вільного (після завершення прийому).
     */
    public void markAvailable() {
        this.available = true;
    }

    // --- ГЕТЕРИ ---

    public String getDoctorId()      { return doctorId; }
    public String getFullName()      { return fullName; }
    public String getSpecialization(){ return specialization; }
    public boolean isAvailable()     { return available; }

    // --- СПЕЦІАЛЬНІ МЕТОДИ ---

    @Override
    public String toString() {
        // Повертаємо читабельне представлення лікаря
        return String.format("Doctor{id='%s', name='%s', spec='%s', available=%b}",
                doctorId, fullName, specialization, available);
    }

    /**
     * Два лікарі рівні, якщо у них однаковий doctorId.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                   // Той самий об'єкт у пам'яті
        if (!(obj instanceof Doctor other)) return false; // Різні типи — не рівні
        return Objects.equals(doctorId, other.doctorId); // Порівнюємо за ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId); // Хеш теж базується на doctorId
    }
}
