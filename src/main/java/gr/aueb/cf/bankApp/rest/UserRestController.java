package gr.aueb.cf.bankApp.rest;

import gr.aueb.cf.bankApp.core.exceptions.*;
import gr.aueb.cf.bankApp.dto.TransferReadOnlyDTO;
import gr.aueb.cf.bankApp.dto.UserReadOnlyDTO;
import gr.aueb.cf.bankApp.dto.UserRegistrationDTO;
import gr.aueb.cf.bankApp.mapper.Mapper;
import gr.aueb.cf.bankApp.model.TransferTransaction;
import gr.aueb.cf.bankApp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/api")
    public class UserRestController {
        private final UserService userService;
        private final Mapper mapper;


        @GetMapping("/users")
        public ResponseEntity<Page<UserReadOnlyDTO>> getPaginatedUsers(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "1") int size){

            Page<UserReadOnlyDTO> usersPage = userService.getPaginatedUsers(page,size);
            return new ResponseEntity<>(usersPage, HttpStatus.OK);
        }

    @PostMapping("/registerAsCustomer")
    public ResponseEntity<UserReadOnlyDTO> saveUserAsCustomer(
            @Valid @RequestPart(name = "user") UserRegistrationDTO userRegistrationDTO,
            BindingResult bindingResult
    ) throws InvalidArgumentException, ValidationException, ObjectAlreadyExistsException, AppServerException {
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        UserReadOnlyDTO userReadOnlyDTO = userService.saveUserCustomer(userRegistrationDTO);
        return new ResponseEntity<>(userReadOnlyDTO, HttpStatus.OK);
    }

    @PostMapping("/registerAsAdmin")
    public ResponseEntity<UserReadOnlyDTO> saveUserAsAdmin(
            @Valid @RequestPart(name = "user")UserRegistrationDTO userRegistrationDTO,
            BindingResult bindingResult
    ) throws InvalidArgumentException , ValidationException , ObjectAlreadyExistsException, AppServerException{
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }
        UserReadOnlyDTO userReadOnlyDTO = userService.saveUserCustomer(userRegistrationDTO);
        return new ResponseEntity<>(userReadOnlyDTO, HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferReadOnlyDTO> makeTransfer(@RequestBody TransferTransaction dto) throws InvalidArgumentException, ObjectNotFoundException {
            TransferReadOnlyDTO result = userService.saveTransfer(dto);
            return new ResponseEntity<>(result,HttpStatus.OK);

        }
}


