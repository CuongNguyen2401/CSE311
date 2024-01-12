package com.fastfood.service.impl;

//CanvasjsChartData.java

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.service.IPieChartService;

@Component
public class PieChartService implements IPieChartService {

	enum Category {
		FOOD(1), DRINK(2);

		private long value;

		Category(long value) {
			this.value = value;
		}

	}

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	public List<List<Map<Object, Object>>> getCanvasjsChartData() {
		Map<Object, Object> map = null;
		List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
		List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();

		double numberOfFoodProduct = productService.countByCategoryId(Category.FOOD.value);
		

		int totalProduct = productService.getTotalItem();
		Double foodPercentage = (double) (numberOfFoodProduct / totalProduct) * 100;

		foodPercentage = (double) Math.round(foodPercentage * 10) / 10;

		map = new HashMap<Object, Object>();
		map.put("label", Category.FOOD);
		map.put("y", foodPercentage);
		dataPoints1.add(map);
		map = new HashMap<Object, Object>();
		map.put("label", Category.DRINK);
		map.put("y", (100 - foodPercentage));
		dataPoints1.add(map);
		list.add(dataPoints1);

		return list;
	}

}