# Team5-VMCS
Team5-Vending Machine Control System (VMCS)

© 2020
The information contained in this document is the property of NUS. The contents must not be copied in whole or in part for purposes other than which it has been supplied without the consent of ISS, or, if it has been furnished under contract to another party, as expressly authorized under that contract, then ISS shall not be liable for any errors or omissions.

1.	INTRODUCTION 

1.1	Background 
The Cool Drinks Company requires the development and deployment of control software for their new range of Quencher 2020 drinks dispensing machines. The work will be organised as two projects: 
(1)	Design and implementation of a Vending Machine Control System (VMCS) digital twin that will run as a standalone Windows application on a standard Pentium PC, but will provide all the necessary vending functions that are required by the real Quencher 2020 drinks dispensing machine. 
(2)	Modification of the VMCS digital twin software so that it will run on the internal processor of the Quencher 2020 drinks dispensing machine, and will provide all the functionality necessary to control the real vending machine. 
The aim of the first of the projects is to enable the Cool Drinks Company management to confirm their requirements for the system functionality, including the user interface, and for the software developers to demonstrate the adequacy of their implementation of the core functionality. It is only in the second project when the full system is created. However, to minimise the effort expended, the digital twin software should be developed in such a way that minimal changes are required to modify it to meet the requirements of the second project.  
The aim of this document is to present the user requirement specification for the VMCS digital twin. 

1.2	Objectives 
The objectives of this document are to: 
(1)	define the functional requirements for the VMCS digital twin; 
(2)	define the non-functional (ie: operational and quality) requirements that would be necessary to support the functional requirements; 
(3)	identify the necessary requirements that would facilitate the future modification of digital twin software to operate the real Quencher 2020 drinks dispensing machine; and 
(4)	provide the basis for the development of the system.

1.3	Organization 
Section 1 gives an introduction to this document. Section 2 presents an overview of requirements for the system. The functional requirements are presented in Section 3 and section 4 describes the operational and quality requirements.

1.4	Scope 
This user requirement specification is solely concerned with the development of the VMCS digital twin software, and its future modification to operate on the real Quencher 2020 drinks dispensing machine. It is not concerned with the physical requirements for the development of the real vending machine, such as the real user interface or the operation of electrical/mechanical components of the vending machine.

1.5	Definition of Terms
The following terms have special meanings within this document: 
(1)	The word shall implies a mandatory requirement. 
(2)	The word should implies a desirable requirement. 
(3)	The word will implies a mandatory requirement outside the scope of this document. 
(4)	The word may implies a desirable requirement outside the scope of this document.

2.	OVERVIEW OF REQUIREMENTS

2.1	Introduction 
The Cool Drinks Company operates and maintains a large number of automatic soft drinks dispensers at various sites throughout Singapore. Previously these machines have been controlled by analog devices, which are now out of production and also difficult to maintain. It has been decided to invest in a computerized control system that will be installed in a new range of soft drinks vending machines - the Quencher 2020 drinks dispensing machine. These machines will sell several different brands of drinks.

2.2	System Perspective 
Currently, the analog controllers installed in existing Quencher machines accept coins from customers wishing to purchase soft drinks cans and activates the dispensing of these drinks cans. It allows the selection of particular brands, and indicates whether a particular brand of soft drink is unavailable. It also allows the vending machine maintainer to monitor the stocks levels of coins and drinks cans held, and remove all the cash from the machine. The new system will duplicate the functionality of the existing system and add new functionality made feasible by software technology.

2.3	System Functions 
System functions to be implemented are: 
(1)	Acceptance/rejection and cumulative totaling of coins entered by customers. 
(2)	Dispensing of a drinks can of a selected brand to the customer. 
(3)	Indication of whether the brands are available (or whether they are out-of stock). 
(4)	Dispensing of change to customers. 
(5)	Displaying of requested information to the vending machine maintainer. 
(6)	Setting of new prices for drinks brands by the vending machine maintainer. 
(7)	Issue of all the coins held within machine to the maintainer. 
(8)	Simulated user interfaces for the customer and maintainer, and an additional user interface to allow the state of the vending machine to be monitored and for the stocks levels of coins and drinks cans held to be changed.

2.4	User Characteristics 
The users will play one or more of the following roles: 
	acting as customers of the vending machine, who would be members of the general public; 
	acting as the vending machine maintainer; or 
	controllers of the overall digital twin of the VMCS. 
There will only be one user of the VMCS digital twin at any time. However, the user should be able to play more than one role while using the system.

2.5	General Constraints
The VMCS digital twin software should be designed and implemented so as to minimise the future work needed to modify it to create the software to control the real Quencher 2020 drinks dispensing machine.

3.	FUNCTIONAL REQUIREMENTS 
This section describes the functional requirements of the VMCS digital twin. Figure 1 below present an overview of the system, showing the main components of the application and the roles of the users. The functional requirements are structured in terms of: 
(1)	Processing requirements – the main software functions to be provided to achieve the VMCS digital twin. 
(2)	User interface requirements – the required user interface to support the software functions. 
(3)	Data initialization and storage requirements – the required mechanism for initializing and saving the data used to simulate the internal state of the vending machine. 

3.1	Processing Requirements 
The system is required to provide the software functions necessary for the application to initiated/terminated, and for the customer, maintainer and controller to perform their roles in using the system. These functions are described in the following subsections. 

3.1.1	Initiating and Terminating the VMCS Digital twin 
The following are required to enable the controller to initiate the VMCS digital twin: 
(1)	When the program is initiated from the operating system, the system is required to display the simulator control panel (ie: the main menu). 
(2)	The system is required to enable the controller to initiate the digital twin by pressing a button on the simulator control panel. This simulates switching on the power supply in the real vending machine. 
(3)	After starting the digital twin (ie: switching-on), the system is required to enable the controller to display the three other user interface panels by pressing buttons on the simulator control panel. These panels are: 
	Customer panel. 
	Maintenance panel. 
	Machinery simulator panel. 
Note that when each panel is displayed, it is required to present the current values of its data items (ie: the panels should be correctly initialised). 
(4)	When the users have completed using the digital twin, the system is required to enable the controller to exit from digital twin and the application by pressing a button on the simulator control panel. This simulates switching off the power supply in the real vending machine. 

3.1.2	Customer Requirements 
The following are required to enable the customer to purchase drinks cans from the vending machine: 
(1)	The system is required to monitor the number of drinks cans of each brand and display their name, availability (in-stock or out-of-stock) and price on the customer panel. Note that the price of each brand will not necessarily be the same. 
(2)	The system is required to enable the customer to select a drinks brand from those available by using the customer panel. Note that no coins shall be permitted to be entered until a drinks brand has been selected. 
(3)	The system is required to enable the customer to enter coins to pay for the selected drinks brand. The system is required to check whether the coins entered are valid (by using their weight as a means to determine their identity), and: 
	accept the coin if it is valid; 
	reject the coin if it is invalid and indicate this to the customer on the customer panel. 
(4)	The system is required to monitor the amount of accepted money entered by the customer, and: 
	display the total amount of money entered on the customer panel after each coin has been entered; 
	determine when enough money has been entered to purchase the selected drinks brand. 
(5)	If a customer selects an unavailable drinks brand, then the system shall not respond, and shall wait for the customer to choose an available drinks brand. 
(6)	When the system determines that the customer has entered enough money, it is then required to dispense a drinks can of the selected brand to the customer via the customer panel.  
(7)	When the system has successfully dispensed the drinks can, it is then required to determine whether any change should be given by comparing the total amount of money entered with the price of the brand.  
(8)	To issue change, the system is required to monitor the number of coins held within the vending machine and their value, and determine if it is possible to pay the customer the required change. The following actions are then required: 
	If there are enough coins of appropriate denominations to make a sum of money equal to the value of the change required, then coins of appropriate denominations shall be issued to the customer via the customer panel. 
	If there are not enough coins of appropriate denominations held within the vending machine to make a sum of money equal to the value of the change required, then the system is required to determine the value of the largest of amount of change (less than the required amount) that is available in the machine, and issue coins of appropriate denominations to the customer via the customer panel. Also an appropriate message shall be displayed on the customer panel.  
(9)	Prior to the dispensing of the drinks can, the system is required to enable the customer to terminate the transaction. After a purchase has been terminated, the system is required to return the coins entered to the customer via the customer panel. 
(10)	In the real vending machine, faults in the machinery may prevent the selected brand from being dispensed (eg: the can might get stuck) or may prevent the change or refund from being issued. The handling of such faults is required to be simulated in the VMCS digital twin. If such faults do occur, then the current customer transaction shall be terminated, the money entered by the customer shall be refunded (if feasible) and further customer transactions shall be prevented until the maintainer and logged-in and logged-out of the system (the assumption being that the maintainer would have fixed the problem) 

3.1.3	Maintainer Requirements 
The following are required to enable to the maintainer to perform the vending machine maintenance tasks: 
(1)	To gain access to the maintenance functions, the maintainer is required to enter an authorized password into the maintenance panel. The system is required to process the entered password, and only permit the maintainer to use the functions of the maintenance panel when the correct password is received. This password shall be fixed. When the correct password is received, the system is required to unlock the door of the vending machine. Note that when the door is in the unlocked state, access can be gained to add or remove coins or cans from the machine. 
(2)	When the maintainer successfully logs-in to the system, the system is required to terminate any customer transactions that are in-progress, and refund any money that has been entered during the transaction. Further, the system is required to prevent any customer transactions from commencing while the maintainer is logged-in. 
(3)	The system is required to enable the (logged-in) maintainer to view the following information: 
	The numbers of drinks cans of each brand held by the machine. 
	The number of coins of each denomination held by the machine. 
	The total amount of money (ie: the total value of the coins) held by the machine. 
(4)	The system is required to enable the (logged-in) maintainer to change the price of each drinks brand. 
(5)	The system is required to enable the (logged-in) maintainer to be issued with all the cash held by the vending machine. 
(6)	The system is required to enable the (logged-in) maintainer to formally exit from maintenance panel (ie: log-out). However, the maintainer will only be permitted to log-out if the vending machine door has been relocked. After the maintainer has logged-out, the customer panel shall be updated to reflect changes in drinks brand prices, drinks brand availability and change availability. Also, customer transactions, using the customer panel, shall be permitted to resume. 

3.1.4	Machinery Simulator Requirements 
The following are required to enable to the controller to monitor the internal state of the machine, and simulate the replenishment of coin and drink stocks and the locking of the vending machine door (which would be physical activities of the maintainer on the real vending machine, not covered by the maintainer’s software functions): 
(1)	The system is required enable the controller to monitor the internal state of the coin and drinks stocks held by the machine, by viewing their current values on the machinery simulator panel. When drinks cans are dispensed or coins are received or issued (eg: as change), the panel is required to be updated to show the latest values. 
(2)	When the maintainer successfully logs-in to the maintenance panel, the system is required to simulate the unlocking of the vending machine door. As a result, the unlocked door state is required to be displayed on the machinery simulator panel. 
(3)	Once the door has been unlocked, the system is required to enable the controller to use the machinery simulator panel to change the following: 
	Number of cans of each brand held by the machine. 
	Number of coins of each denomination held by the machine. 
Note that these values can only be changed when the door state is unlocked. Otherwise the values can only be viewed. 
(4)	The system is required to enable the controller to simulate the locking of the vending machine door by using the machinery simulator panel. 

3.2	User Interface Requirements 
The system is required to provide user interface panels to enable the customer, maintainer and controller to effectively perform their roles in using VMCS digital twin. The requirements for these user interface panels are described in the following subsections. 

3.2.1	Simulator Control Panel 
The system is required to provide a main menu to enable the user to access the main system functions, by providing a simulator control panel as shown below in figure 2. The required features are as follows: 
(1)	There shall be a facility to begin the digital twin (corresponding to the main power being turned on for the vending machine). This shall be achieved by pressing a button with the caption “Begin Simulation”. 
(2)	There shall be a facility to end the digital twin and the application (which will be equivalent to the main power being turned off). This shall be achieved by pressing a button with the caption “End Simulation”. 
(3)	There shall be the facility to display any of the other user interface panels. Each panel shall be displayed by pressing a button with the following captions: 
	“Activate Customer Panel”. 
	“Activate Maintenance Panel”. 
	“Activate Machinery Panel”. 

3.2.2	Customer Panel 
The system is required to simulate the customer interface panel of the real vending machine, by providing a customer panel as shown below in figure 3. The required features are as follows: 
(1)	There shall be a set of buttons, displayed vertically, representing each drinks brand held by the machine. Next to each brand button, the price of the brand shall be displayed. A brand shall be selected by pressing the appropriate button. 
(2)	On selection of an available brand, all the brand buttons shall be inactivate until the end of the transaction (ie: they will not respond if pressed).
(3)	If a drinks brand is unavailable, the caption  “Not in Stock” shall be highlighted in line with the corresponding brand button. The buttons corresponding to unavailable brands shall be inactive (ie: they will not respond if pressed). 
(4)	There shall be a set of buttons below the caption “Enter Coins Here”, each representing a coin of a specific denomination, including a button representing an “Invalid Coin”, which will allow the simulated input of coins. A coin shall be entered by pressing the appropriate button. 
(5)	On selection of an available brand, all the coins buttons shall be activated (ie: they will respond if pressed). Before a brand has been selected, coin buttons shall be inactive (ie: they will not respond if pressed). 
(6)	If an invalid coin is entered, then the “Invalid Coin” caption shall be highlighted, and the invalid coin shall appear in a panel next to the caption “Collect Coins”. 
(7)	The total money inserted to date during a customer transaction shall be displayed next to the “Total Money Inserted” caption. 
(8)	When the required amount of money has been entered (ie: greater than or equal to the price of the selected brand) then a drinks can shall be dispensed. This shall be simulated by displaying an appropriate text message next to the caption “Collect Can Here”. 
(9)	If change is required, but no change (or less than the correct change) is available in the machine then the caption ”No Change Available” shall be highlighted. 
(10)	If change is required and (at least some change is) available, then the value shall appear in a panel next to the caption “Collect Coins”. 
(11)	If the drinks can has not yet been dispensed, then the transaction shall be terminated if required by pressing the button with the caption “Terminate and Return Cash”. Pressing this 
button shall cause the value of the returned money to appear in a panel next to the caption “Collect Coins”. The system shall then be ready for the next customer transaction.

3.2.3	Maintenance Panel 
The system is required to simulate the maintainer interface panel of the real vending machine, by providing a maintenance panel as shown below in figure 4. The required features are as follows: 
(1)	There shall be a text field next to the caption “Password”. A password, comprising six alphanumeric characters, shall be able to be entered into the text field. 
(2)	If the valid password is entered, then the caption “Valid Password” shall be highlighted. 
(3)	If an invalid password is entered, then the caption “Invalid Password” shall be highlighted. 
(4)	Until a valid password is entered, the remainder of the maintenance panel shall be inactive (ie: the functions can not be used). 
(5)	There shall be a set of buttons below the caption “Quantity of Coins Available”, displayed vertically, representing each coin denomination held by the machine. If one of these buttons is pressed then the number of coins held in machine, corresponding to the denomination of the button, shall be displayed in a panel to the right of the buttons. 
(6)	There shall be a set of buttons below the caption “Quantity of Drinks Available”, displayed vertically, representing each drinks brand held by the machine. If one of these buttons is pressed then the number of cans held in machine, corresponding to the brand of the button, shall be displayed in a panel to the right of the buttons. In addition, the price of the selected brand shall be displayed next to the caption “Brand Price”. 
(7)	The price of the currently selected brand shall be able to be changed by entering a new price, in Singapore cents, into the text field to overwrite the current price displayed next to the caption “Brand Price”. 
(8)	The total amount of cash held within the machine shall be displayed by pressing the button with the caption “Show Total Cash Held”. The total cash will then be displayed next to the button. 
(9)	The total amount of cash held within the machine shall be issued to the user by pressing the button with the caption “Press to Collect All Cash”. The total cash shall then be displayed next to the caption “Collect Cash”. The machine shall then contain zero coins of each denomination. 
(10)	The user (ie: the maintainer) shall terminate use of the maintenance panel (ie: log-out) by pressing a button with the caption “Press Here when Finished”. If the state of the vending machine door is locked, then the log-out request shall be successful and the maintenance panel shall become inactive (ie: the functions can not be used) except for the “Password” text field. However, if the state of the vending machine door is unlocked, then the log-out request shall be ignored.

3.2.4	Machinery Simulator Panel 
The system is required to provide user interface facilities to enable the controller to observe and change the stocks of coins and cans held by the machine, and also to observe and change the status of the door (ie: lock the door). This shall be achieved by providing a machinery simulator panel as shown below in figure 5. The required features are as follows: 
(1)	The number of coins of each denomination held by the machine shall be displayed vertically beneath the caption “Quantity of Coins”. Each denomination shall be displayed by a caption (indicating the denomination name) and a text field showing the number of coins held. The number of coins held of a denomination shall be able to be changed by entering a new value into the text field to overwrite the current value (note that the value must be an integer,  0 &  40). 
(2)	The number of cans of each brand held by the machine shall be displayed vertically beneath the caption “Quantity of Cans”. Each brand shall be displayed by a caption (indicating the brand name) and a text field showing the number of cans held. The number of cans held of a brand shall be able to be changed by entering a new value into the text field to overwrite the current value (note that the value must be an integer,  0 &  20). 
(3)	The current status of the vending machine door lock (locked or unlocked) shall be displayed as a checkbox next to the caption “Door Locked”. When the maintainer successfully logs-in this checkbox shall be automatically unchecked by the system to indicate that the door status is unlocked. The door status shall be able to be changed to locked by checking the checkbox. 
(4)	Note that the initial status for the vending machine door lock shall be locked. 

3.3	Data Initialisation and Storage Requirements 
The system is required to enable the basic data required to run the VMCS digital twin to be defined by the user in an input file. The file shall be read-in by the system and used for data initialisation purposes. The input file is required to enable the user to define the following data items: 
(1)	The brand to be offered from each of the vending slots of the machine, and the initial quantity of the brand held in each slot. Note that the same brand can be offered by several vending slots. 
(2)	The denominations of coin that are accepted by the machine, and the initial quantity of coins of each denomination held by the machine. 
(3)	For each brand, the following shall be able to be defined: a name (to be displayed) and a price. 
(4)	For each coin denomination, the following shall be able to be defined: a name (to be displayed), a weight and a value (in Singapore cents). Note that the weight is used to determine the denomination of a coin when it is entered. 
When the application terminates, the current state of the data items defined above are required to saved to the input file so that they can used as input to subsequent runs of the VMCS digital twin.  

4.	OPERATIONAL AND QUALITY REQUIREMENTS 

4.1	Operating Environment 
The VMCS digital twin shall run on a standard Pentium PC under the Windows operating system. 

4.2	Development Constraints 
The software shall be designed using the use case driven approach and shall be coded using the Java programming language. 

4.3	System Interfaces 
The VMCS digital twin shall be a standalone application. Hence it is not required to interface with any other application or device. However, when the next project is undertaken, to covert the digital twin to become the control software for the Quencher 2020 drinks dispensing machine, the software will be expected to interface with: 
(1)	The coin entry and validation mechanisms. 
(2)	The coin storage mechanism. 
(3)	The drinks can storage and dispenser mechanisms. 
(4)	The real customer and maintenance panels. 
The digital twin software shall be constructed such that it can be straightforwardly changed to interface with these hardware mechanisms/panels. 

4.4	Performance 
Since this is a single user system, and the processing loads are minimal, there is no started requirement concerning the performance of the system. 

4.5	Reliability 
The VMCS digital twin is designed to allow users to test out the control software before it is modified to become the control system for the real vending machine. Hence there is no stated requirement concerning the reliability of the system. However, software from the digital twin that is expected to be part of the real control system should be thoroughly tested during the digital twin’s development phase. 

4.6	Availability 
The system should be able to be installed in a straightforward manner on the PC of each user. It should be always available for use except for the maintenance duration. Any maintenance work that is necessary should require a mean downtime for the system of no more than 2 hours per day. 

4.7	Capacity and Expandability 
Initially the system shall cater for five drink brands and five coin denominations. However, the software should be constructed such that up to ten brands and ten denominations can be catered for. 

4.8	Security 
Access to operate the functions available to the maintainer shall be restricted by use of the correct password. 

4.9	Future Growth 
The VMCS digital twin software should be designed such that only minimal changes are required to modify it to meet the requirements of the control system for the real vending machine. 
