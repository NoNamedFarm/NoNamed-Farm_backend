package com.nonamed.farm.global.exception.handler;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nonamed.farm.global.exception.NonamedException;

public class NonamedExceptionFilter extends OncePerRequestFilter {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		try {
			filterChain.doFilter(request, response);
		} catch (NonamedException e) {
			response.setStatus(e.getErrorCode().getStatus());

			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");

			ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
			response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
		}
	}

}
