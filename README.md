Purple Mountain Hiker Simulator
A Java-based simulation designed to manage hiker safety and trail traffic at "Purple Mountain." This project demonstrates the practical application of Stacks and Queues to handle real-world logistics, data persistence, and GUI-driven interaction.

🚀 Features
Automated Hiker Registration: Generates unique hiker IDs and processes them into standardized groups.

Dynamic Data Structures: * Stacks: Used to group hikers (10 per stack).

Queues: Used to manage trail entrances, ensuring a "First-In, First-Out" (FIFO) flow for hikers entering the mountain.

Time Simulation: A built-in clock simulates the 7:00 AM to 9:00 AM window, processing groups every 30 simulated minutes.

File I/O: Automatically generates .txt logs of hikers as they enter and exit the trails.

Mountain Manager Mode (Advanced): An intelligent routing toggle that switches from random trail assignment to a load-balancing algorithm based on queue length.

🛠 High-Level Logic & Architecture
The simulation follows a specific lifecycle to ensure data integrity:

Grouping: Hikers are pushed onto a Stack. Once the stack reaches a capacity of 10, the entire stack is moved to a trail entrance.

Queuing: Each of the three trail entrances maintains a Queue of stacks.

Mountain Manager Mode: * Default: Stacks are assigned to trails randomly.

Manager Mode: The program compares trailQueue.size() across all three entrances and assigns the hiker group to the shortest line to optimize throughput.

💻 How to Use
Configure: Enter the desired number of hikers in the "Num of Hikers" field (Default is 156).

Start: Click the Start button to register hikers, fill the stacks, and populate the trail queues.

Open Trail: Click Open Trail Entrances. The simulation will begin.

Note: The clock will update every few seconds to represent 30-minute intervals. Wait until the clock reaches 9:00 AM.

Close Trails: Once the mountain is "closed," click Close Trails to finalize the logs and see the total hiker count for each entrance in the text area.

📊 Technical Implementation
Stack Implementation: Array-based Stack chosen for its efficiency in grouping a fixed number of elements.

Queue Implementation: Linked List Queue chosen to handle the unpredictable number of groups arriving at an entrance while maintaining arrival order.
