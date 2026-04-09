package ua.khpi.oop.lab02;

public class Main {
    public static void main(String[] args) {
        Clinic clinic = new Clinic("Приватна клініка 'Здоров'я'");

        Doctor d1 = new Doctor("D-01", "Іван Коваленко", "Терапевт");
        Doctor d2 = new Doctor("D-02", "Олена Марченко", "Хірург");
        Patient p1 = new Patient("P-01", "Андрій Бондаренко", 1990);
        Patient p2 = new Patient("P-02", "Наталія Сидоренко", 1985);

        clinic.addDoctor(d1);
        clinic.addDoctor(d2);
        clinic.addPatient(p1);
        clinic.addPatient(p2);

        Appointment appt = clinic.scheduleAppointment(p1, d1, "2026-04-15");
        System.out.println(clinic);
        System.out.println(appt);
        System.out.println("Пацієнт має активний запис: " + p1.hasActiveAppointment());
    }
}