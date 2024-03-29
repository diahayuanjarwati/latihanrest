package com.eksad.latihanrest.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

 //many to one karena ada brand ada produk. satu brand punya lebih dari satu produk. dan diliat dari sisi prpduk, jadinya pake many to one
@Data
@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public class Product {
	
	@ApiModelProperty(value = "Product ID (Primary key)")
	@Id
	//konsep sequence : kyk counter di database. 
	//jd initinya mau update data ke databasae berdasarkan disesuaikan product_id yg sudah ada di database
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id")

	//	@SequenceGenerator(name = "product_id"
//							, sequenceName = "product_id_seq"
//							, allocationSize = 1)
	
	//ini identity bisa dipake atau jd optional kalau ga pake sequence
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ApiModelProperty(value = "Brand's Name", required = true)
	@ManyToOne
	@JoinColumn(name = "brand_id") // utk ke kolom di database
	private Brand brand;
	
	@ApiModelProperty(value = "Brand ID", required = true)
	@Transient // agar variabel di bawah ini gak dibaca bahwa kolom di database
	private Long brandId;
	
	@ApiModelProperty(value = "Product's Name", required = true)
	@Column(nullable = false)
	private String name;
	
	@ApiModelProperty(value = "Product's Prics per Pcs", required = true)
	@Column(nullable = false)
	private BigDecimal price ; //jangan pake double, tp pake bigdecimal. karna uang/harga pakenya bigdecimal aja
	
	

}
