# Spring PetClinic 画面遷移エントリポイント一覧

## FIND OWNERS 画面

### Add Owner
* `OwnerController.initCreationForm()`  
  **URL:** `/owners/new`  
  **ファイル:** [OwnerController.java](src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java)

### Find Owner
* `OwnerController.processFindForm()`  
  **URL:** `/owners?lastName=`  
  **ファイル:** [OwnerController.java](src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java)

---

## OWNER DETAILS 画面

### Edit Owner
* `OwnerController.initUpdateOwnerForm()`  
  **URL:** `/owners/{ownerId}/edit`  
  **ファイル:** [OwnerController.java](src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java)

### Add New Pet
* `PetController.initCreationForm()`  
  **URL:** `/owners/{ownerId}/pets/new`  
  **ファイル:** [PetController.java](src/main/java/org/springframework/samples/petclinic/owner/PetController.java)

### Add Visit
* `VisitController.initNewVisitForm()`  
  **URL:** `/owners/{ownerId}/pets/{petId}/visits/new`  
  **ファイル:** [VisitController.java](src/main/java/org/springframework/samples/petclinic/owner/VisitController.java)

---

## CREATE OR UPDATE OWNER 画面

### Save Owner
* `OwnerController.processCreationForm()`  
  **URL:** `/owners/new`  
  **ファイル:** [OwnerController.java](src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java)

* `OwnerController.processUpdateOwnerForm()`  
  **URL:** `/owners/{ownerId}/edit`  
  **ファイル:** [OwnerController.java](src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java)

---

## CREATE OR UPDATE PET 画面

### Save Pet
* `PetController.processCreationForm()`  
  **URL:** `/owners/{ownerId}/pets/new`  
  **ファイル:** [PetController.java](src/main/java/org/springframework/samples/petclinic/owner/PetController.java)

* `PetController.processUpdateForm()`  
  **URL:** `/owners/{ownerId}/pets/{petId}/edit`  
  **ファイル:** [PetController.java](src/main/java/org/springframework/samples/petclinic/owner/PetController.java)

---

## VISIT FORM 画面

### Save Visit
* `VisitController.processNewVisitForm()`  
  **URL:** `/owners/{ownerId}/pets/{petId}/visits/new`  
  **ファイル:** [VisitController.java](src/main/java/org/springframework/samples/petclinic/owner/VisitController.java)

---

## RESERVATIONS 画面

### HOSPITAL RESERVATIONS

#### Hospital Reservation List
* `HospitalReservationController.showReservationList()`  
  **URL:** `/reservations/hospital`  
  **ファイル:** [HospitalReservationController.java](src/main/java/org/springframework/samples/petclinic/owner/HospitalReservationController.java)

#### Add New Hospital Reservation
* `HospitalReservationController.initCreationForm()`  
  **URL:** `/owners/{ownerId}/pets/{petId}/reservations/hospital/new`  
  **ファイル:** [HospitalReservationController.java](src/main/java/org/springframework/samples/petclinic/owner/HospitalReservationController.java)

* `HospitalReservationController.processCreationForm()`  
  **URL:** `/owners/{ownerId}/pets/{petId}/reservations/hospital/new`  
  **ファイル:** [HospitalReservationController.java](src/main/java/org/springframework/samples/petclinic/owner/HospitalReservationController.java)

#### Edit Hospital Reservation
* `HospitalReservationController.initUpdateForm()`  
  **URL:** `/reservations/hospital/{reservationId}/edit`  
  **ファイル:** [HospitalReservationController.java](src/main/java/org/springframework/samples/petclinic/owner/HospitalReservationController.java)

* `HospitalReservationController.processUpdateForm()`  
  **URL:** `/reservations/hospital/{reservationId}/edit`  
  **ファイル:** [HospitalReservationController.java](src/main/java/org/springframework/samples/petclinic/owner/HospitalReservationController.java)

#### Prescription List
* `PrescriptionController.showPrescriptionList()`  
  **URL:** `/reservations/hospital/{reservationId}/prescriptions`  
  **ファイル:** [PrescriptionController.java](src/main/java/org/springframework/samples/petclinic/owner/PrescriptionController.java)

#### Add New Prescription
* `PrescriptionController.initCreationForm()`  
  **URL:** `/reservations/hospital/{reservationId}/prescriptions/new`  
  **ファイル:** [PrescriptionController.java](src/main/java/org/springframework/samples/petclinic/owner/PrescriptionController.java)

* `PrescriptionController.processCreationForm()`  
  **URL:** `/reservations/hospital/{reservationId}/prescriptions/new`  
  **ファイル:** [PrescriptionController.java](src/main/java/org/springframework/samples/petclinic/owner/PrescriptionController.java)

---

### TRIMMING RESERVATIONS

#### Trimming Appointment List
* `TrimmingAppointmentController.showTrimmingList()`  
  **URL:** `/reservations/trimming`  
  **ファイル:** [TrimmingAppointmentController.java](src/main/java/org/springframework/samples/petclinic/owner/TrimmingAppointmentController.java)

#### Add New Trimming Appointment
* `TrimmingAppointmentController.initCreationForm()`  
  **URL:** `/owners/{ownerId}/pets/{petId}/reservations/trimming/new`  
  **ファイル:** [TrimmingAppointmentController.java](src/main/java/org/springframework/samples/petclinic/owner/TrimmingAppointmentController.java)

* `TrimmingAppointmentController.processCreationForm()`  
  **URL:** `/owners/{ownerId}/pets/{petId}/reservations/trimming/new`  
  **ファイル:** [TrimmingAppointmentController.java](src/main/java/org/springframework/samples/petclinic/owner/TrimmingAppointmentController.java)

#### Edit Trimming Appointment
* `TrimmingAppointmentController.initUpdateForm()`  
  **URL:** `/reservations/trimming/{appointmentId}/edit`  
  **ファイル:** [TrimmingAppointmentController.java](src/main/java/org/springframework/samples/petclinic/owner/TrimmingAppointmentController.java)

* `TrimmingAppointmentController.processUpdateForm()`  
  **URL:** `/reservations/trimming/{appointmentId}/edit`  
  **ファイル:** [TrimmingAppointmentController.java](src/main/java/org/springframework/samples/petclinic/owner/TrimmingAppointmentController.java)

#### Delete Trimming Appointment
* `TrimmingAppointmentController.deleteAppointment()`  
  **URL:** `/reservations/trimming/{appointmentId}/delete`  
  **ファイル:** [TrimmingAppointmentController.java](src/main/java/org/springframework/samples/petclinic/owner/TrimmingAppointmentController.java)

---

### HOTEL RESERVATIONS

#### Hotel Stay List
* `HotelStayController.showHotelStayList()`  
  **URL:** `/reservations/hotel`  
  **ファイル:** [HotelStayController.java](src/main/java/org/springframework/samples/petclinic/owner/HotelStayController.java)

#### Add New Hotel Stay
* `HotelStayController.initCreationForm()`  
  **URL:** `/owners/{ownerId}/pets/{petId}/reservations/hotel/new`  
  **ファイル:** [HotelStayController.java](src/main/java/org/springframework/samples/petclinic/owner/HotelStayController.java)

* `HotelStayController.processCreationForm()`  
  **URL:** `/owners/{ownerId}/pets/{petId}/reservations/hotel/new`  
  **ファイル:** [HotelStayController.java](src/main/java/org/springframework/samples/petclinic/owner/HotelStayController.java)

#### Edit Hotel Stay
* `HotelStayController.initUpdateForm()`  
  **URL:** `/reservations/hotel/{stayId}/edit`  
  **ファイル:** [HotelStayController.java](src/main/java/org/springframework/samples/petclinic/owner/HotelStayController.java)

* `HotelStayController.processUpdateForm()`  
  **URL:** `/reservations/hotel/{stayId}/edit`  
  **ファイル:** [HotelStayController.java](src/main/java/org/springframework/samples/petclinic/owner/HotelStayController.java)

#### Billing for Hotel Stay
* `BillingController.showBillingForm()`  
  **URL:** `/stays/{stayId}/billing`  
  **ファイル:** [BillingController.java](src/main/java/org/springframework/samples/petclinic/owner/BillingController.java)

* `BillingController.processBilling()`  
  **URL:** `/stays/{stayId}/billing`  
  **ファイル:** [BillingController.java](src/main/java/org/springframework/samples/petclinic/owner/BillingController.java)

---

## ERROR 画面

### Trigger Exception
* `CrashController.triggerException()`  
  **URL:** `/oups`  
  **ファイル:** [CrashController.java](src/main/java/org/springframework/samples/petclinic/system/CrashController.java)