package com.sony.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {
	private String message;
}
