package ua.khpi.oop.lab02; // Оголошуємо пакет — це "папка" для наших класів

import java.util.Objects; // Імпортуємо утиліту для зручної роботи з equals/hashCode

/**
 * Represents a patient registered in the private clinic.
 * Each patient has a unique ID, full name, birth year, and a doctor assigned to them.
 *
 * @author Student
 */
public class Patient {

    // --- ПОЛЯ (стан об'єкта) ---
    // private означає, що поля недоступні напряму ззовні — це інкапсуляція
    private final String patientId;   // Унікальний ідентифікатор пацієнта (не змінюється після створення — final)
    private String fullName;          // Повне ім'я пацієнта
    private int birthYear;            // Рік народження
    private boolean hasAppointment;   // Чи є активний запис до лікаря
    private Doctor doctor;            // Лікар, якому призначено пацієнта (посилання на інший об'єкт)

    // --- КОНСТРУКТОРИ ---

    /**
     * Повний конструктор — ініціалізує всі поля явно.
     * Використовується, коли відомі всі дані про пацієнта.
     *
     * @param patientId        унікальний ідентифікатор пацієнта
     * @param fullName         повне ім'я
     * @param birthYear        рік народження
     * @param hasAppointment   чи є активний запис
     * @param doctor           призначений лікар
     */
    public Patient(String patientId, String fullName, int birthYear,
                   boolean hasAppointment, Doctor doctor) {
        // this.поле = параметр — так розрізняємо поле класу і параметр конструктора
        this.patientId = patientId;
        this.fullName = fullName;
        this.birthYear = birthYear;
        this.hasAppointment = hasAppointment;
        this.doctor = doctor;
    }

    /**
     * Скорочений конструктор — створює пацієнта без запису і без лікаря.
     * hasAppointment = false, doctor = null за замовчуванням.
     * Використовується при первинній реєстрації.
     *
     * @param patientId  унікальний ідентифікатор
     * @param fullName   повне ім'я
     * @param birthYear  рік народження
     */
    public Patient(String patientId, String fullName, int birthYear) {
        // Викликаємо повний конструктор через this(...) — уникаємо дублювання коду
        this(patientId, fullName, birthYear, false, null);
    }

    // --- МЕТОДИ ПРЕДМЕТНОЇ ГАЛУЗІ ---

    /**
     * Записує пацієнта на прийом до лікаря.
     * Якщо лікар не null — призначаємо і ставимо прапор запису.
     *
     * @param doctor лікар, до якого записуємо пацієнта
     */
    public void scheduleAppointment(Doctor doctor) {
        if (doctor != null) {           // Перевіряємо, що лікар справді існує
            this.doctor = doctor;       // Призначаємо лікаря пацієнту
            this.hasAppointment = true; // Встановлюємо прапор: є запис
        }
    }

    /**
     * Скасовує запис пацієнта — знімає прапор запису.
     * Лікар залишається призначеним, але активного запису більше немає.
     */
    public void cancelAppointment() {
        this.hasAppointment = false; // Просто скидаємо прапор
    }

    /**
     * Перевіряє, чи є у пацієнта активний запис до лікаря.
     *
     * @return true якщо є запис
     */
    public boolean hasActiveAppointment() {
        return hasAppointment; // Повертаємо значення поля
    }

    // --- ГЕТЕРИ (доступ до приватних полів ззовні) ---

    public String getPatientId() { return patientId; }
    public String getFullName()  { return fullName; }
    public int getBirthYear()    { return birthYear; }
    public Doctor getDoctor()    { return doctor; }

    // --- СПЕЦІАЛЬНІ МЕТОДИ ---

    /**
     * Повертає рядкове представлення об'єкта.
     * Використовується при System.out.println(patient).
     */
    @Override // @Override означає, що ми перевизначаємо метод із батьківського класу Object
    public String toString() {
        // String.format — зручний спосіб форматування рядка з підстановкою значень
        return String.format("Patient{id='%s', name='%s', birthYear=%d, appointment=%b, doctor=%s}",
                patientId, fullName, birthYear, hasAppointment,
                doctor != null ? doctor.getFullName() : "none"); // Якщо лікар є — показуємо ім'я, інакше "none"
    }

    /**
     * Два пацієнти вважаються рівними, якщо у них однаковий patientId.
     * Ім'я може збігатися, але ідентифікатор — унікальний ключ.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                    // Порівнюємо з самим собою — одразу true
        if (!(obj instanceof Patient other)) return false; // Якщо obj не Patient — false (pattern matching Java 16+)
        return Objects.equals(patientId, other.patientId); // Рівність за унікальним ідентифікатором
    }

    /**
     * hashCode має бути узгоджений з equals:
     * якщо equals повертає true — hashCode мусить бути однаковим.
     * Базуємось на тому самому полі, що й equals — patientId.
     */
    @Override
    public int hashCode() {
        return Objects.hash(patientId); // Objects.hash обчислює хеш від одного або кількох значень
    }
}
