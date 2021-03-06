package com.demo.springbootapis.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.demo.springbootapis.model.ContactUsInfo;
import com.demo.springbootapis.service.ContactService;

@RestController
@RequestMapping("/contactus")
@CrossOrigin
public class ContactUsController {

  @Autowired
  ContactService contactService;

  // Get All contact note messages
  @GetMapping()
  public List<ContactUsInfo> getAllMessages() {
    return contactService.getAllMessages();
  }

  // Create a new note message
  @PostMapping()
  public ContactUsInfo createMessage(@Valid @RequestBody ContactUsInfo info) {
    return contactService.createMessage(info);
  }

  // Get a single not message
  @GetMapping("/{id}")
  public ResponseEntity<ContactUsInfo> getMessageById(@PathVariable(value = "id") Integer id) {
    ContactUsInfo message = contactService.getMessageById(id);
    if (message == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(message);
  }

  // Update a note message
  @PutMapping("/{id}")
  public ContactUsInfo updateMessage(@PathVariable(value = "id") Integer id,
      @Valid @RequestBody ContactUsInfo info) {
    return contactService.updateMessage(info);
  }

  // Delete a note (set as resolved and leave in database)
  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void deleteMessage(@PathVariable(value = "id") Integer id) {
    contactService.deleteMessage(id);
  }

}
