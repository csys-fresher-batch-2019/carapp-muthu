package com.chainsys.carsale.service;

import com.chainsys.carsale.dao.CarDetailDAO;
import com.chainsys.carsale.dao.impl.CarDetailImp;
import com.chainsys.carsale.model.CarDetail;
import com.chainsys.carsale.util.DbException;

public class CarDetailService {
	
	private CarDetailDAO carDetailDAO = new CarDetailImp();

	public void addCarDetail(CarDetail carDetail) throws ServiceException {
		try {
			//carvalidator.validate(carDetail);
			carDetailDAO.addCarDetail(carDetail);
		} catch (DbException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	
}
