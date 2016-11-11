import React from 'react';
import {List, ListItem} from 'material-ui/List';
import Avatar from 'material-ui/Avatar';
import Divider from 'material-ui/Divider';
import MobileTearSheet from './MobileTearSheet';
import TextField from 'material-ui/TextField';

class UpcomingMatches extends React.Component {

	constructor(props) {
		super(props);
		this.state = {matches: []};
	}

	componentDidMount() {
       fetch('http://localhost:8080/api/matches/search/findByStatus?status=SCHEDULED')
       .then((response) => { return response.json() })
       .then( (json) => {this.setState({matches: json._embedded.matches}); });
	}

	render() {
        var matchNodes = this.state.matches.map(function (match, i) {
			var homeTeamName = match.homeTeamName;
			var awayTeamName = match.awayTeamName;
			var homeTeamLogoSrc = "/images/logos/" + homeTeamName + ".png";
			var awayTeamLogoSrc = "/images/logos/" + awayTeamName + ".png";

            return (
				<List >
					<ListItem
						primaryText = {homeTeamName}
						rightAvatar = { <Avatar> <TextField underlineShow={false} inputSytle={{textAlign:'center'}} hintText="Hint"/> </Avatar> }
						leftAvatar = {<Avatar src = {homeTeamLogoSrc} />} />
					<ListItem
						primaryText = {awayTeamName}
						rightAvatar = { <TextField hintText="Hint"/> }
						leftAvatar = {<Avatar src = {awayTeamLogoSrc} />} />
				</List>
            );
        });

		var mobileTearSheets = matchNodes.map(function (matchNode, i) {
			var sheets = [];
				if (i % 4 == 0) {
					const sheet = (
						<MobileTearSheet key={i}>
							{matchNodes[i]}
							<Divider/>
							{matchNodes[i+1]}
							<Divider/>
							{matchNodes[i+2]}
							<Divider/>
							{matchNodes[i+3]}
						</MobileTearSheet>
					);
					sheets.push(sheet);
				}

			return sheets;
		});

        return (
			<div style={{display: 'flex', flexWrap: 'wrap'}}>
				{mobileTearSheets}
			</div>
        );
	}
}

export default UpcomingMatches;