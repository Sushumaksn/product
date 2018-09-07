package com.example.Product.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Product.model.Item;
import com.example.Product.model.ResultItem;
import com.example.Product.service.ProductService;

@RestController("/")
public class ProductRestController {
	// @Autowired
	ProductService service = new ProductService();

	@PostMapping("/add")
	public ResponseEntity<String> addProduct(@RequestBody Item item, Model model) {

		service.addProduct(item);
		return new ResponseEntity<String>("{\"message\":\"Product added successfully\"}", HttpStatus.OK);
	}

	@GetMapping("/productsList/{reportDate}")
	public ResponseEntity<List<ResultItem>> getProductList(@PathVariable("reportDate") String reportDate)
			throws ParseException {
		Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(reportDate);
		List<ResultItem> items = service.getProductList(date1);
		return new ResponseEntity<List<ResultItem>>(items, HttpStatus.OK);
	}

}