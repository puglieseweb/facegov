package com.facegov.lib.logginghandler;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Binding;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.slf4j.Logger;

/**
 * Simple abstract handler class for logging in-bound and out-bound SOAP messages.
 * This class is thread safe.
 */
public abstract class AbstractLoggingHandler implements SOAPHandler<SOAPMessageContext> {

	private static final String INCOMING = "Incoming ";
	private static final String OUTGOING = "Outgoing ";
	private static final String ENCODING = "UTF-8";

	/**
	 * Gets the logger for the logging handler.
	 * @return The logger
	 */
	public abstract Logger getLogger();

	/**
	 * Logs the in-bound and out-bound messages.
	 * @param context the context.
	 * @return always returns true.
	 */
	@Override
	public boolean handleMessage(final SOAPMessageContext context) {
		return log(context);
	}

	/**
	 * Logs the fault messages.
	 * @param context the context.
	 * @return always returns true.
	 */
	@Override
	public boolean handleFault(final SOAPMessageContext context) {
		return log(context);
	}

	/**
	 * Logs the message.
	 * @param context the context.
	 * @return always returns true.
	 */
	private boolean log(final SOAPMessageContext context) {
		try {
        	final SOAPMessage message = context.getMessage();
        	final ByteArrayOutputStream stream = new ByteArrayOutputStream();
            message.writeTo(stream);
            final String xml = stream.toString(ENCODING);
            final boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
            getLogger().info((outboundProperty ? OUTGOING : INCOMING) + xml);
        } catch (final Exception e) {
        	getLogger().error("Error logging from SOAPMessageContext", e);
        }
		return true;
	}

	@Override
	public void close(final MessageContext context) {
		// No resources to close
	}

	@Override
	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

	/**
     * Handler method to log requests/response for third party calls.
     * @param clientBinding the client binding containing the existing handler chain.
     * @param abstractLoggingHandler the handler to add to the chain.
     * @return the clientBinding with the handler added.
     */
	public Binding configureLoggingHandler(final Binding clientBinding, final AbstractLoggingHandler abstractLoggingHandler) {
            @SuppressWarnings("rawtypes")
			final List<Handler> handlerList = clientBinding.getHandlerChain();
            handlerList.add(abstractLoggingHandler);
            clientBinding.setHandlerChain(handlerList);
            return clientBinding;
    }

}
