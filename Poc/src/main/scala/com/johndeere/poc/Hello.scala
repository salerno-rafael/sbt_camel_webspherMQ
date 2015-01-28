package main.scala.com.johndeere.poc

import org.apache.camel.scala.dsl.builder.RouteBuilder
import org.apache.camel.CamelContext
import org.apache.camel.impl.DefaultCamelContext
import javax.jms.ConnectionFactory
import org.apache.camel.component.jms.JmsComponent
import com.ibm.mq.jms.MQQueueConnectionFactory
import org.apache.camel.Processor
import org.apache.camel.scala.dsl.builder.RouteBuilderSupport
import org.apache.camel.Exchange
import com.ibm.mq.jms.JMSC
import org.apache.camel.Message
import org.apache.camel.CamelContext
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.scala.dsl.builder.RouteBuilder
import javax.jms.ConnectionFactory
import org.apache.camel.component.jms.JmsComponent

object Hello {

	def main(args: Array[String]) {
		readQueue
	}


	def readQueue = {
			try {
				val context: CamelContext = new DefaultCamelContext()
			val cf: MQQueueConnectionFactory = new MQQueueConnectionFactory()
				cf.setHostName("MQGWD2.SDDE.DEERE.COM")
				cf.setPort(2171)
				cf.setTransportType(1)
				cf.setQueueManager("MQGWD2")
				cf.setChannel("DY.FDS.CLIENT")

				context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(cf))

				val endpoint = context.getEndpoint("jms:DY.FINANCIALDOC.IDE")
        val produce = context.createProducerTemplate().sendBody("jms:DY.FINANCIALDOC.IDE","hello")
   
          
				val consumer = endpoint.createPollingConsumer()
				val  exchange = consumer.receive()

				println(exchange.getIn.getBody)

			} catch {
			case e: Exception => println("exception caught: " + e);
			}
	}

}

