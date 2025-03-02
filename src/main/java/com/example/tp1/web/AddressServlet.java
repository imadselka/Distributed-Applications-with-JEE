package com.example.tp1.web;

import com.example.tp1.model.Address;
import com.example.tp1.service.AddressBookService;
import com.example.tp1.service.ServiceException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/address/*")
public class AddressServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AddressBookService service = AddressBookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // List all addresses
            List<Address> addresses = service.getAllAddresses();
            request.setAttribute("addresses", addresses);
            request.setAttribute("addressBookName", service.getAddressBookName());
            request.getRequestDispatcher("/WEB-INF/views/list-addresses.jsp").forward(request, response);
        } else if (pathInfo.equals("/new")) {
            // Form for adding a new address
            request.getRequestDispatcher("/WEB-INF/views/address-form.jsp").forward(request, response);
        } else if (pathInfo.equals("/search")) {
            // Handle search
            String searchType = request.getParameter("searchType");
            String searchTerm = request.getParameter("searchTerm");

            try {
                List<Address> results;
                if ("person".equals(searchType)) {
                    results = service.findAddressByPersonName(searchTerm);
                } else if ("city".equals(searchType)) {
                    results = service.findAddressByCity(searchTerm);
                } else {
                    results = service.getAllAddresses();
                }

                request.setAttribute("addresses", results);
                request.setAttribute("searchPerformed", true);
                request.setAttribute("searchTerm", searchTerm);
                request.getRequestDispatcher("/WEB-INF/views/list-addresses.jsp").forward(request, response);
            } catch (ServiceException e) {
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // Add a new address
            String personName = request.getParameter("personName");
            String streetName = request.getParameter("streetName");
            String streetNumber = request.getParameter("streetNumber");
            String cityName = request.getParameter("cityName");

            Address address = new Address(personName, streetName, streetNumber, cityName);

            try {
                service.addAddress(address);
                response.sendRedirect(request.getContextPath() + "/address/");
            } catch (ServiceException e) {
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
            }
        } else if (pathInfo.equals("/delete")) {
            // Delete an address
            String personName = request.getParameter("personName");
            String streetName = request.getParameter("streetName");
            String streetNumber = request.getParameter("streetNumber");
            String cityName = request.getParameter("cityName");

            Address address = new Address(personName, streetName, streetNumber, cityName);

            try {
                boolean removed = service.removeAddress(address);
                if (removed) {
                    request.setAttribute("message", "Address removed successfully");
                } else {
                    request.setAttribute("message", "Address not found");
                }
                response.sendRedirect(request.getContextPath() + "/address/");
            } catch (ServiceException e) {
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}