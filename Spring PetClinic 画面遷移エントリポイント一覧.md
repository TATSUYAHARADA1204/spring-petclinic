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

## ERROR 画面

### Trigger Exception
* `CrashController.triggerException()`  
  **URL:** `/oups`  
  **ファイル:** [CrashController.java](src/main/java/org/springframework/samples/petclinic/system/CrashController.java)