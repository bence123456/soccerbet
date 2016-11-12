import React from 'react';
import {List, ListItem} from 'material-ui/List';
import Avatar from 'material-ui/Avatar';
import Divider from 'material-ui/Divider';
import MobileTearSheet from './MobileTearSheet';
import TextField from 'material-ui/TextField';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import {Link} from 'react-router'

const leftMarginStyle = {
	marginLeft: '100px'
};

class UpcomingMatches extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
		    matches: [],
		    errorText: '',
		    value: props.value
		}
		this.onChange = this.onChange.bind(this);
	}

    onChange(event) {
        if (event.target.value.match('[0-9]')) {
            this.setState({ errorText: '' })
        } else {
            this.setState({ errorText: '0..99 között pls!' })
        }
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
						rightAvatar = { <Avatar> <TextField underlineShow={false} inputStyle={{textAlign:'center'}} style={{width: '20px'}}
						        hintStyle={{textAlign:'center', width: '20px'}} hintText="0" onChange={this.onChange} /> </Avatar> }
						leftAvatar = {<Avatar src = {homeTeamLogoSrc} />} />
					<ListItem
						primaryText = {awayTeamName}
						rightAvatar = { <Avatar> <TextField underlineShow={false} inputStyle={{textAlign:'center'}} style={{width: '20px'}}
						        hintStyle={{textAlign:'center', width: '20px'}} hintText="0" onChange={this.onChange} /> </Avatar> }
						leftAvatar = {<Avatar src = {awayTeamLogoSrc} />} />
				</List>
            );
        }, this);

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

        var times = this.state.matches.map(function (match, i) {
            return match.dateTime.replace("T"," ").substring(0,16);
        });

        if (this.state.matches.length > 0) {
            return (
			    <div style={{display: 'flex', flexWrap: 'wrap', marginLeft: '80px'}}>
                    <Card>
                    <CardText> Minden mérkőzés kezdési időpontja: {times[0]} </CardText>
                    </Card>
                    {mobileTearSheets}
			    </div>
            );
        } else {
			return (
                <div>
                    <h4 style={leftMarginStyle}>
                        Jelenleg nincs kiírt mérkőzés a német kupában, kérlek nézz vissza később!
                    </h4>
                    <FlatButton label="Vissza a kezdőlapra" containerElement={<Link to="/"/>} backgroundColor= "white" style={leftMarginStyle} />
                </div>
			);
		}
	}
}

export default UpcomingMatches;