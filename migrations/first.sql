CREATE DATABASE `digital_nao`; /*!40100 DEFAULT CHARACTER SET latin1 */;

use `digital_nao`;

-- digital_nao.authors definition
CREATE TABLE `authors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `google_api_author_id` varchar(15) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `affiliations` varchar(200) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `authors_google_api_author_id_IDX` (`google_api_author_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
