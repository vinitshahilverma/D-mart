package com.dmart.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(StoreException.class)
    public ResponseEntity<MyErrorDetails> StoreExceptionHandler(StoreException ee,WebRequest req){
    	
    	MyErrorDetails err = new MyErrorDetails();
    	
    	  err.setTimeStamp(LocalDateTime.now());
    	  err.setDetails(req.getDescription(false));
    	  err.setMessage(ee.getMessage());
    	  
    	  return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
    	
    }
	
	@ExceptionHandler(ProductException.class)
    public ResponseEntity<MyErrorDetails> productExceptionHandler(ProductException ee,WebRequest req){
    	
    	MyErrorDetails err = new MyErrorDetails();
    	
    	  err.setTimeStamp(LocalDateTime.now());
    	  err.setDetails(req.getDescription(false));
    	  err.setMessage(ee.getMessage());
    	  
    	  return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
    	
    }
	
	@ExceptionHandler(CategoryException.class)
    public ResponseEntity<MyErrorDetails> categoryExceptionHandler(CategoryException ee,WebRequest req){
    	
    	MyErrorDetails err = new MyErrorDetails();
    	
    	  err.setTimeStamp(LocalDateTime.now());
    	  err.setDetails(req.getDescription(false));
    	  err.setMessage(ee.getMessage());
    	  
    	  return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
    	
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> exceptionHandler(Exception ee,WebRequest req){
    	
    	MyErrorDetails err = new MyErrorDetails();
    	
    	  err.setTimeStamp(LocalDateTime.now());
    	  err.setDetails(req.getDescription(false));
    	  err.setMessage(ee.getMessage());
    	  
    	  return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
    	
    }
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandlerExceptionHandler(NoHandlerFoundException ee,WebRequest req){
		
		MyErrorDetails err = new MyErrorDetails();
		
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ee.getMessage());
		err.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> MethodArgumentExceptionHandler(MethodArgumentNotValidException ee){
		
		MyErrorDetails err = new MyErrorDetails();
		
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage("Validation error");
		err.setDetails(ee.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	

}
