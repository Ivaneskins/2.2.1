package hiber.dao;


import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addCar(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> getListCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    public Car getCarByUserName(String userName) {
        List<Car> cars = sessionFactory.getCurrentSession().createQuery("SELECT c FROM Car c WHERE c.user.firstName = " +
                        ":name", Car.class)
                .setParameter("name", userName)
                .getResultList();
        return cars.get(0);
    }
}
