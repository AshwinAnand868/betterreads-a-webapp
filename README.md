Are you a passionate reader who loves to read a lot of books but occasionally struggles to keep track of them? Or perhaps you're someone who simply wants to maintain a record of the books you've read and are currently reading for personal fulfilment. Maybe you're even intrigued by the idea of an app that can store information of all the books ever published in the world with minimal chances of experiencing downtime as the number of users increases while still delivering a seamless user experience. If any of these scenarios resonate with you, allow me to introduce you to an app I've developed as part of an online series— "Better Reads."

"Better Reads" is designed based on the idea of "Goodreads." It allows users to easily track their books and offers additional features to enhance the reading experience. When you open the app, you'll be presented with a home page where you can either log in using your GitHub account or search for books without logging in. However, the tracking functionality is only available to logged-in users. Once you've decided to log in, you can track a book by navigating to its specific page. On the book page, you can enter the start and end dates of your reading journey. Additionally, there's a dropdown menu where you can select one of three options: "Currently Reading," "Finished," or "Did Not Finish." These options allow you to mark the status of the book for yourself. If you've finished reading a book and would like to provide a rating for future readers, you can do so by giving a rating from 1 to 5 stars.

While the home page initially prompts you to log in, once you're already logged in, it transforms to shows you all the books you've read or are currently reading. This arrangement makes it convenient for you to access your ongoing reads with the currently reading book placed at the top.

In terms of technology, "Better Reads" uses Spring Boot, Spring Security, Apache Cassandra, Spring Data for data communication, OAuth2 for login, Thymeleaf templates for view pages and various other web technologies. This app is highly scalable, performant, and reliable, thanks to its reliance on Apache Cassandra Database. Cassandra is a distributed NoSQL Database that stores data across multiple nodes/machines and ensures replication. Think of it as a large box of toffees that you divide and pack into smaller packets/boxes for your convenience. Same does this application with all those user’s data. However, this is just a small analogy and Cassandra does a lot more than that. If you have ever wondered how big corporations like Amazon, Facebook, Netflix, or others are able to manage that whole lots of data with a great user experience, then this is because they use Apache Cassandra under the hood!