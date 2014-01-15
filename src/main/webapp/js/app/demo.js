require({
    baseUrl: '/js',
    paths: {
        'jquery': 'lib/jquery-1.10.2',
        'jquery.cometd': 'lib/cometd/jquery.cometd',
        'org/cometd': 'lib/cometd/cometd'
    },
    shim: {
    	'jquery.cometd': {
    		exports: 'jquery.cometd',
    		deps: ['org/cometd']
    	},
    	'org/cometd': {
    		exports: 'org/cometd'
    	}
    }
}, ['jquery', 'jquery.cometd'], function($, cometd) {
	cometd.configure({
		url: location.protocol + '//' + location.host + config.contextPath + '/cometd',
		logLevel: 'info'
	});
	
	cometd.addListener('/meta/handshake', function(message) {
		if (message.successful) {
			$('#messages').append('<div>CometD handshake successful</div>');
        } else {
        	$('#messages').append('<div>CometD handshake failed</div>');
        }
	});
	
	cometd.handshake();
});
