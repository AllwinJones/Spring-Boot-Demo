package com.allwin.first.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="autocomplete")
public class AutoComplete {

	@Id
	private String uniqId;
	
	private Integer addressId;
	
}
