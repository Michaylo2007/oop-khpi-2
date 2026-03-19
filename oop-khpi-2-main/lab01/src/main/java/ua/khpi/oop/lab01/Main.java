package ua.khpi.oop.lab01;

/**
 * Entry point for the demonstration of the private clinic registration model.
 * Shows object creation, domain methods, and special methods (toString, equals, hashCode).
 *
 * @author Student
 */
public class Main {

    public static void main(String[] args) {

        // ============================================================
        // 1. СТВОРЕННЯ ОБ'ЄКТІВ
        // ============================================================

        // Створюємо лікаря через повний конструктор (вказуємо available явно)
        Doctor doctorFull = new Doctor("D-01", "Ivan Kovalenko", "Therapist", true);

        // Створюємо лікаря через скорочений конструктор (available = true за замовчуванням)
        Doctor doctorShort = new Doctor("D-02", "Olena Marchenko", "Surgeon");

        System.out.println("=== Лікарі ===");
        System.out.println(doctorFull);   // Викликається toString() автоматично
        System.out.println(doctorShort);

        // Пацієнт через скорочений конструктор (без запису і лікаря)
        Patient patient1 = new Patient("P-01", "Andriy Bondarenko", 1990);

        // Пацієнт через повний конструктор (з лікарем і з записом)
        Patient patient2 = new Patient("P-02", "Natalia Sydorenko", 1985, true, doctorFull);

        // Ще один пацієнт з тим самим ID, що і patient1 — для перевірки equals
        Patient patient1copy = new Patient("P-01", "Andriy Bondarenko", 1990);

        System.out.println("\n=== Пацієнти ===");
        System.out.println(patient1);
        System.out.println(patient2);

        // ============================================================
        // 2. ВИКЛИК МЕТОДІВ ПРЕДМЕТНОЇ ГАЛУЗІ
        // ============================================================

        System.out.println("\n=== Запис на прийом ===");

        // patient1 поки без запису
        System.out.println("patient1 має запис: " + patient1.hasActiveAppointment()); // false

        // Записуємо patient1 до вільного лікаря
        patient1.scheduleAppointment(doctorFull);
        System.out.println("Після scheduleAppointment: " + patient1.hasActiveAppointment()); // true
        System.out.println(patient1); // toString покаже оновлений стан

        // Скасовуємо запис
        patient1.cancelAppointment();
        System.out.println("Після cancelAppointment: " + patient1.hasActiveAppointment()); // false

        // Перевіряємо, чи лікар може прийняти пацієнта
        System.out.println("\ndoctorFull доступний: " + doctorFull.canAcceptPatient()); // true
        doctorFull.markBusy(); // Позначаємо лікаря як зайнятого
        System.out.println("Після markBusy: " + doctorFull.canAcceptPatient()); // false
        doctorFull.markAvailable(); // Повертаємо доступність

        // Створюємо запис (Appointment)
        Appointment appt1 = new Appointment("A-001", patient2, doctorFull, "2026-04-10");
        Appointment appt2 = new Appointment("A-002", patient1, doctorShort, "2026-04-15", "SCHEDULED");

        System.out.println("\n=== Записи ===");
        System.out.println(appt1);
        System.out.println("appt1 активний: " + appt1.isActive()); // true

        appt1.complete(); // Завершуємо перший прийом
        System.out.println("Після complete: " + appt1);
        System.out.println("appt1 активний: " + appt1.isActive()); // false

        appt2.cancel(); // Скасовуємо другий запис
        System.out.println("Після cancel: " + appt2);

        // ============================================================
        // 3. ДЕМОНСТРАЦІЯ toString(), equals(), hashCode()
        // ============================================================

        System.out.println("\n=== toString() ===");
        // toString() викликається автоматично при передачі об'єкта в println
        System.out.println("patient1: "     + patient1);
        System.out.println("patient1copy: " + patient1copy);
        System.out.println("doctorFull: "   + doctorFull);

        System.out.println("\n=== equals() ===");
        // patient1 і patient1copy мають однаковий ID → мають бути рівні
        System.out.println("patient1.equals(patient1copy): " + patient1.equals(patient1copy)); // true

        // patient1 і patient2 мають різні ID → нерівні
        System.out.println("patient1.equals(patient2): "     + patient1.equals(patient2));     // false

        // Порівняння лікарів з однаковим і різними ID
        Doctor doctorCopy = new Doctor("D-01", "Ivan Kovalenko", "Therapist");
        System.out.println("doctorFull.equals(doctorCopy): " + doctorFull.equals(doctorCopy));  // true
        System.out.println("doctorFull.equals(doctorShort): "+ doctorFull.equals(doctorShort)); // false

        System.out.println("\n=== hashCode() ===");
        // Якщо equals() = true, то hashCode() МУСИТЬ бути однаковим — це контракт Java
        System.out.println("patient1 hashCode:     " + patient1.hashCode());
        System.out.println("patient1copy hashCode: " + patient1copy.hashCode());
        System.out.println("Однакові hashCode? "     + (patient1.hashCode() == patient1copy.hashCode())); // true

        System.out.println("patient2 hashCode:     " + patient2.hashCode()); // Інший — інший ID
    }
}
