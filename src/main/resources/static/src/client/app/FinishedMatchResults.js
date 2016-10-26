import React from 'react';
import {List, ListItem} from 'material-ui/List';
import Divider from 'material-ui/Divider';
import Avatar from 'material-ui/Avatar';

class ListExampleContacts extends React.Component {
	render() {
		return (
			<div>
				<List>
					<ListItem
						primaryText="Chelsea Otakan"
						rightAvatar={ <Avatar> 4 </Avatar> }
						leftAvatar={<Avatar src="../images/Bremer Sv.png" />} />
					<ListItem
						primaryText="Eric Hoffman"
						rightAvatar={ <Avatar> 4 </Avatar> }
						leftAvatar={<Avatar src="../images/Dynamo Dresden.png" />} />
				</List>
				<Divider />
				<List>
					<ListItem
						primaryText="Adelle Charles"
						rightAvatar={ <Avatar> 1 </Avatar> }
						leftAvatar={<Avatar src="../images/FC Augsburg.png" />} />
					<ListItem
						primaryText="Adham Dannaway"
						rightAvatar={ <Avatar> 1 </Avatar> }
						leftAvatar={<Avatar src="../images/FSV Frankfurt.png" />} />
				</List> 
			</div>
		)
	}
};

export default ListExampleContacts;