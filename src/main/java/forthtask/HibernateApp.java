package forthtask;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateApp {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                                                            .addAnnotatedClass(Course.class).buildSessionFactory();

        try (Session session = sessionFactory.getCurrentSession()){
            // Начало транзакции
            session.beginTransaction();

//            // Создание объекта
//            Course math = new Course("Math", 120);
//            Course history = new Course("History", 60);
//            Course javaCourse = new Course("Java Course", 300);
//
//            // Сохранение объекта в базе данных
//            session.persist(history);
//            session.persist(javaCourse);
//            session.persist(math);
//            System.out.println(math.getTitle() + " Created");

            // Чтение объекта из базы данных
//            Course mathEmpty = new Course();
            Course retrievedCourse = session.get(Course.class,  1);
            System.out.println("Retrieved course is " + retrievedCourse);

            // Обновление объекта
            retrievedCourse.setTitle("Geometry");
            retrievedCourse.setDuration(40);
            session.merge(retrievedCourse);
            System.out.println("Updated");

            // Удаление объекта
            session.remove(retrievedCourse);
            System.out.println("Deleted");

            session.getTransaction().commit();
            System.out.println("Committed");
        }
    }
}
